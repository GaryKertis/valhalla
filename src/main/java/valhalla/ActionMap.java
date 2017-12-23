package valhalla;

import lombok.EqualsAndHashCode;
import valhalla.Command;
import java.util.HashMap;

import static valhalla.Constants.get;
import static valhalla.Constants.look;

@EqualsAndHashCode
public class ActionMap {
    private HashMap<Command, String> actionMap = new HashMap<>();
    public static String DEFAULT_NO_TAKE = "You can't take the %s.";
    public static String DEFAULT_TAKE = "You take the %s.";
    public static String DEFAULT_LOOK = "You see nothing unusual about the %s.";

    public ActionMap(String name) {
        actionMap.put(get, decorate(DEFAULT_NO_TAKE, name));
        actionMap.put(look, decorate(DEFAULT_LOOK, name));
    }

    public String decorate(String sentence, String name) {
        return String.format(sentence, name);
    }

    public void addAction(Command command, String str) {
        actionMap.put(command, str);
    }

    public String doAction(Command command) {
        if (actionMap.containsKey(command)) {
            return actionMap.get(command);
        } else {
            return "You can't do that right now.";
        }
    }
}
