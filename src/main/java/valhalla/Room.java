package valhalla;

import lombok.Data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

@Data
public class Room {
    private final String INVALID_MOVE = "You can't go that way.";
    private String title;
    private String description;
    private boolean visited = false;
    private HashMap<Direction, Room> adjacentRooms = new HashMap<Direction, Room>();

    private String possibleDirections() {
        String result = "Possible directions: ";
        Iterator<Direction> iterator = adjacentRooms.keySet().iterator();
        while (iterator.hasNext()) {
            result += iterator.next().name();
            if (iterator.hasNext()) result += ",";
        }
        return result;
    }

    public Room(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String printRoom() {
        return this.title + '\n' + this.description + '\n' + possibleDirections();
    }

    public String invalidMove() {
        return INVALID_MOVE;
    }
}
