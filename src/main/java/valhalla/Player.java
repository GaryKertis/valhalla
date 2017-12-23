package valhalla;

import lombok.Data;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Optional;

@Data
public class Player {
    private Room currentRoom;
    private int score;
    private ArrayList<Item> inventory = new ArrayList<>();

    public Player(Room room) {
        currentRoom = room;
    }

    public void takeItem(Item item) throws BadCommandException {

        if (hasItem(item.getName())) {
            throw new BadCommandException(String.format("You already have the %s.", item.getName()));
        }

        if (!currentRoom.hasItem(item.getName())) {
            throw new BadCommandException("That isn't in this room.");
        }

        if (item.isGettable()) {
            inventory.add(item);
            currentRoom.removeItem(item);
        }
    }

    public boolean hasItem(String itemName) {
        return inventory.stream().anyMatch(item -> item.getName().equalsIgnoreCase(itemName));
    }

    public boolean canFindItem(String item) {
        return hasItem(item) || currentRoom.hasItem(item);
    }

    public String printInventory() {
        String result = "You are carrying:" + '\n';
        if (inventory.size() < 1) {
            result += "Nothing.";
            return result;
        }
        Iterator<Item> iterator = inventory.iterator();
        while (iterator.hasNext()) {
            result += iterator.next().getName();
            if (iterator.hasNext()) result += '\n';
        }
        return result;
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

    public String doAction(Construction construction) throws BadCommandException {
        if (construction.getCommand() instanceof Movement) {
            return move((Movement) construction.getCommand());
        }
        switch (CommandList.findByValue(construction.getCommand())) {
            case INVENTORY:
                return printInventory();
            case GET:
                if (construction.getPreposition() != null) {
                    throw new IllegalArgumentException();
                }
                takeItem(construction.getItem1());
                construction.run();
            default:
            return construction.run();
        }
    }

    public String move(Movement move) {
        Room nextRoom = null;
        move = (Movement) move.getOptionalAliasFor().orElse(move);
        nextRoom = currentRoom.getAdjacentRooms().get(move.getDirection());

        if (nextRoom != null) {
            currentRoom = nextRoom;
            return currentRoom.printRoom();
        }
        return currentRoom.invalidMove();
    }
}
