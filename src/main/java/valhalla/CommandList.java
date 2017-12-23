package valhalla;

import lombok.Data;

import java.util.Arrays;

import static valhalla.Constants.*;

public enum CommandList {
    LOOK(look),
    GET(get),
    TAKE(take),
    USE(use),
    GIVE(give),
    PUT(put),
    INVENTORY(inventory),
    I(i),
    // valid movements
    EAST(east),
    WEST(west),
    SOUTH(south),
    NORTH(north),
    UP(up),
    DOWN(down),
    SOUTHEAST(southeast),
    SOUTHWEST(southwest),
    NORTHEAST(northeast),
    NORTHWEST(northwest),
    E(e),
    W(w),
    S(s),
    N(n),
    U(u),
    D(d),
    SE(se),
    SW(sw),
    NE(ne),
    NW(nw);

    CommandList(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return this.command;
    }

    public static CommandList findByValue(Command command) {
        return Arrays.stream(CommandList.values())
                .filter(value -> value.getCommand().equals(command))
                .findFirst()
                .orElse(LOOK);
    }

    private Command command;
}
