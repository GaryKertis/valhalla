package valhalla;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;

@Data
public class Player {
    private Room currentRoom;
    private int score;
    private ArrayList<Item> inventory;

    public Player(Room room) {
        currentRoom = room;
    }

    public void getItem(Item item) {
        inventory.add(item);
    }

    public boolean hasItem(String name) {
        return inventory.stream().anyMatch(item -> item.getName().equalsIgnoreCase(name));
    }

    public boolean canFindItem(String name) {
        return hasItem(name) || currentRoom.hasItem(name);
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
