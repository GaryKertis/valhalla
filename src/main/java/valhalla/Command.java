package valhalla;

import lombok.Data;

@Data
public class Command {
    private String name;
    private boolean transitive = false;
    private Command aliasFor;

    public Command(String name, boolean transitive) {
        this.name = name;
        this.transitive = transitive;
    }

    public Command(String name, boolean transitive, Command aliasFor) {
        this.name = name;
        this.transitive = transitive;
        this.aliasFor = aliasFor;
    }
}
