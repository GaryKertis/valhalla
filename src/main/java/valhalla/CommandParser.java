package valhalla;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

import static valhalla.Constants.*;

public class CommandParser {

    private HashMap<Command, Optional<Item>> parseCommand(String command) throws BadCommandException {

        HashMap<Command, Optional<Item>> result = new HashMap<>();

        String[] split = command.split(" ");

        Command verb = Arrays.stream(CommandList.values())
                .filter(value -> split[0].equals(value.name()))
                .findFirst()
                .orElseThrow(() -> new BadCommandException())
                .getCommand();

//        result.put(CommandType.verb, verb);

        return result;
    }

}
