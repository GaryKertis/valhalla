package valhalla;

import lombok.Data;

import java.util.Optional;

@Data
public class Command {
    private String name;
    private boolean transitive = false;
    private Command aliasFor;
    private String defaultText = "Nothing happens";

    public Command(String name, boolean transitive) {
        this.name = name;
        this.transitive = transitive;
    }

    public Command(String name, Command aliasFor) {
        this.name = name;
        this.aliasFor = aliasFor;
    }
    public Optional<Command> getOptionalAliasFor() {
        return Optional.ofNullable(aliasFor);
    }

}
