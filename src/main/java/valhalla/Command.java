package valhalla;

import lombok.Data;

@Data
public class Command {
    private String name;
    private boolean requiresObject = false;
    private Command aliasFor;

    public Command(String name, boolean requiresObject) {
        this.name = name;
        this.requiresObject = requiresObject;
    }

    public Command(String name, boolean requiresObject, Command aliasFor) {
        this.name = name;
        this.requiresObject = requiresObject;
        this.aliasFor = aliasFor;
    }
}
