package valhalla;

import javax.swing.text.html.Option;
import java.util.*;

import static valhalla.Constants.*;

public class CommandParser {

    public HashMap<Command, Optional<Item>> parseCommand(String command, Player player) throws BadCommandException {

        HashMap<Command, Optional<Item>> result = new HashMap<>();

        ArrayList<String> split = new ArrayList<>(Arrays.asList(command.trim().split(" ")));
        Iterator<String> iterator = split.iterator();

        //TODO:remove articles!

        // the first word must always be a verb
        String rawVerb = iterator.next();
        Command verb = Arrays.stream(CommandList.values())
                .filter(value -> rawVerb.equalsIgnoreCase(value.name()))
                .findFirst()
                .orElseThrow(() -> new BadCommandException("Invalid verb."))
                .getCommand();

        if (!iterator.hasNext()) {
            if (verb.isTransitive()) {
                throw new BadCommandException("Expected object was not found.");
            }
            result.put(verb, null);
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
            String rawItem = iterator.next();
            if (!player.canFindItem(rawItem)) {
                throw new BadCommandException("Can't find that item");
            }
            return result;
        }

        //the next word must be an object that the player can find, since the previous word was not a preposition.
        if (!player.canFindItem(rawItemOrPreposition)) {
            throw new BadCommandException("Can't find that item");
        }

        String item1 = rawItemOrPreposition;

        if (iterator.hasNext()) {
            //next item must be a preposition.
            optionalPreposition = checkPreposition(rawItemOrPreposition);
            if (!optionalPreposition.isPresent()) {
                throw new BadCommandException("Form VERB NOUN VERB or VERB NOUN NOUN is illegal");
            }
        }



//        result.put(CommandType.verb, verb);

        return result;
    }

    public Optional<Preposition> checkPreposition(String text) {
        return Arrays.stream(Preposition.values())
                .filter(value -> text.equalsIgnoreCase(value.name()))
                .findFirst();
    }

}
