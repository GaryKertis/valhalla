package valhalla;

import lombok.Data;

import java.util.Optional;

@Data
public class Movement extends Command {

    private Direction direction;
    private Movement aliasFor;

    public Movement(String name, boolean transitive, Direction direction) {
        super(name, transitive);
        this.direction = direction;
    }

    public Movement(String name, boolean transitive, Movement aliasFor) {
        super(name, transitive);
        this.aliasFor = aliasFor;
    }
}
