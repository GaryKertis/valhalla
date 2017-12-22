package valhalla.rooms;

import lombok.EqualsAndHashCode;
import valhalla.Command;
import java.util.HashMap;

import static valhalla.Constants.get;
import static valhalla.Constants.look;

@EqualsAndHashCode
public class ActionMap {
    private HashMap<Command, String> actionMap = new HashMap<>();

    public ActionMap() {
        actionMap.put(get, "You can't take that.");
        actionMap.put(look, "You see nothing unusual.");
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
