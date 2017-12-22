package valhalla;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

@Data
@NoArgsConstructor
public class Room {
    private final String INVALID_MOVE = "You can't go that way.";
    private String title;
    private String description;
    private boolean visited = false;
    private ArrayList<Item> items = new ArrayList<>();
    private HashMap<Direction, Room> adjacentRooms = new HashMap<Direction, Room>();


    public String possibleDirections() {
        String result = "Possible directions: ";
        Iterator<Direction> iterator = adjacentRooms.keySet().iterator();
        while (iterator.hasNext()) {
            result += iterator.next().name();
            if (iterator.hasNext()) result += ",";
        }
        return result;
    }

    public void addItem(Item item) {
        this.getItems().add(item);
    }

    public void removeItem(Item item) {
        this.getItems().remove(item);
    }

    public boolean hasItem(String itemName) {
        return items.stream().anyMatch(item -> item.getName().equalsIgnoreCase(itemName));
    }

    public Item getItem(String itemName) {
        return items.stream()
                .filter(item -> item.getName().equalsIgnoreCase(itemName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public Room(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String printRoom() {
        return this.getTitle() + '\n' + this.getDescription() + '\n' + possibleDirections();
    }

    public String invalidMove() {
        return INVALID_MOVE;
    }
}
