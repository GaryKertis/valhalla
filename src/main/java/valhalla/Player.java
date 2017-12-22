package valhalla;

import lombok.Data;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Data
public class Player {
    private Room currentRoom;
    private int score;
    private ArrayList<Item> inventory = new ArrayList<>();

    public Player(Room room) {
        currentRoom = room;
    }

    public void takeItem(Item item) {
        inventory.add(item);
    }

    public boolean hasItem(String itemName) {
        return inventory.stream().anyMatch(item -> item.getName().equalsIgnoreCase(itemName));
    }

    public boolean canFindItem(String item) {
        return hasItem(item) || currentRoom.hasItem(item);
    }

    /**
     * TODO:
     * If a player has an item in inventory and there is a conflicting name
     * in the current room the player will not be able to access the inventory
     * item.
     * */
    public Item getItem(String itemName) {
        try {
            return currentRoom.getItem(itemName);
        } catch (NullPointerException | IllegalArgumentException e) {
            return getItemFromInventory(itemName);
        }
    }

    public Item getItemFromInventory(String itemName) {
        return inventory.stream()
                .filter(item -> item.getName().equalsIgnoreCase(itemName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public String doAction(String s) {
        Room nextRoom = null;
        if (s.equals("exit")) {
            System.exit(0);
        }
        if (s.equals(Direction.e.name())) {
            nextRoom = currentRoom.getAdjacentRooms().get(Direction.e);
        }
        if (s.equals(Direction.w.name())) {
            nextRoom = currentRoom.getAdjacentRooms().get(Direction.w);
        }
        if (s.equals(Direction.n.name())) {
            nextRoom = currentRoom.getAdjacentRooms().get(Direction.n);
        }
        if (s.equals(Direction.s.name())) {
            nextRoom = currentRoom.getAdjacentRooms().get(Direction.s);
        }
        if (nextRoom != null) {
            currentRoom = nextRoom;
            return currentRoom.printRoom();
        }
        return currentRoom.invalidMove();
    }
}
