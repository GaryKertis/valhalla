package valhalla;

import valhalla.rooms.LivingRoom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import static valhalla.Constants.*;
import static valhalla.CommandList.*;
public class Main {

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        Room roomOne = initRooms();
        Player player = new Player(roomOne);
        CommandParser parser = new CommandParser(player);
        System.out.println(player.getCurrentRoom().printRoom());
        while (true) {
            String rawCommand = scan.nextLine();
            try {
                Construction construction = parser.parseCommand(rawCommand);
                String response = player.doAction(construction);
                System.out.println(response);
            } catch (BadCommandException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static Room initRooms() {
        Room roomOne = new LivingRoom();
        Room roomTwo = new Room("[Your Parent's Lake House, Kitchen]", "You are standing in the kitchen.");
        Room roomThree = new Room("[Your Parent's Lake House, Entryway]", "You are standing in the entryway.");
        roomTwo.getAdjacentRooms().put(Direction.w, roomOne);
        roomOne.getAdjacentRooms().put(Direction.e, roomTwo);
        roomOne.getAdjacentRooms().put(Direction.n, roomThree);
        roomThree.getAdjacentRooms().put(Direction.s, roomOne);
        roomOne.getItems().add(new Item("key"));
        roomOne.getItems().add(new Item("window"));
        return roomOne;
    }

    public static void initCommands() {


//        look(LOOK),
//                get(GET),
//                take(GET),
//                use(USE),
//                put(PUT),
//                give(PUT),
//                move(MOVE),
//                go(MOVE);
    }



}
