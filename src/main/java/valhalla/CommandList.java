package valhalla;

import lombok.Data;

import static valhalla.Constants.*;

public enum CommandList {
    LOOK(look),
    GET(get),
    TAKE(take),
    USE(use),
    GIVE(give),
    PUT(put),
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

    private Command command;
}
