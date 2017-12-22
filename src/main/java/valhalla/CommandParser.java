package valhalla;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;

import static valhalla.Constants.*;

@Data
@AllArgsConstructor
public class CommandParser {

    Player player;

    public Construction parseCommand(String command) throws BadCommandException {

        Construction result = new Construction();

        ArrayList<String> split = new ArrayList<>(Arrays.asList(command.trim().split(" ")));
        split = stripUnnecessaryWords(split);
        Iterator<String> iterator = split.iterator();

        // the first word must always be a verb
        String rawVerb = iterator.next();
        Command verb = Arrays.stream(CommandList.values())
                .filter(value -> rawVerb.equalsIgnoreCase(value.name()))
                .findFirst()
                .orElseThrow(() -> new BadCommandException("Invalid verb."))
                .getCommand();
        verb = verb.getOptionalAliasFor().orElse(verb);
        result.setCommand(verb);

        if (!iterator.hasNext()) {
            if (verb.isTransitive()) {
                throw new BadCommandException("Expected object was not found.");
            }
            return result;
        }

        //the second word must always be a noun that we recognize or a preposition.
        String rawItemOrPreposition = iterator.next();
        Optional<Preposition> optionalPreposition = checkPreposition(rawItemOrPreposition);
        if (optionalPreposition.isPresent()) {
            Preposition preposition = optionalPreposition.get();
            if (!iterator.hasNext()) {
                throw new BadCommandException("Preposition without object");
            }
            //next word MUST be an object that the player can currently find.
            Item item = getItem(iterator.next());
            result.setItem1(item);

            //no more words allowed
            if (iterator.hasNext()) {
                throw new BadCommandException("Words exist beyond expected end of command");
            }

            return result;
        } else {
            //next word MUST be an object that the player can currently find.
            Item item = getItem(rawItemOrPreposition);
            result.setItem1(item);
        }

        if (iterator.hasNext()) {
            //next item must be a preposition.
            optionalPreposition = checkPreposition(iterator.next());
            if (!optionalPreposition.isPresent()) {
                throw new BadCommandException("Form VERB NOUN VERB or VERB NOUN NOUN is illegal");
            }
            result.setPreposition(optionalPreposition.get());

            if (!iterator.hasNext()) {
                throw new BadCommandException("Preposition without object");
            }

            //final word must be the second object.
            Item item = getItem(iterator.next());
            result.setItem2(item);
        }

        //no more words allowed
        if (iterator.hasNext()) {
            throw new BadCommandException("Words exist beyond expected end of command");
        }

        return result;
    }

    public Item getItem(String item) throws BadCommandException {
        try {
            return player.getItem(item);
        } catch (IllegalArgumentException e) {
            throw new BadCommandException("You don't see any " + item);
        }
    }

    public Optional<Preposition> checkPreposition(String text) {
        return Arrays.stream(Preposition.values())
                .filter(value -> text.equalsIgnoreCase(value.name()))
                .findFirst();
    }

    public ArrayList<String> stripUnnecessaryWords(ArrayList<String> str) {
        return str.stream()
                .filter(word -> !Articles.has(word))
                .filter(word -> !Adjectives.has(word))
                .collect(Collectors.toCollection(ArrayList::new));
    }

}
