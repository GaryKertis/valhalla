package valhalla;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import static valhalla.Constants.*;
public class Main {

    private ArrayList<Command> commands = new ArrayList<>();

    public static void main(String args[]) {
        Room roomOne = initRooms();
        Scanner scan = new Scanner(System.in);
        Player player = new Player(roomOne);
        System.out.println(player.getCurrentRoom().printRoom());
        while (true) {
            String rawCommand = scan.next();
            String response = player.doAction(rawCommand);
            System.out.println(response);
        }
    }

    public static Room initRooms() {
        Room roomOne = new Room("[Your Parent's Lake House, Living Room]", "You are standing in your parents lake house.");
        Room roomTwo = new Room("[Your Parent's Lake House, Kitchen]", "You are standing in the kitchen.");
        Room roomThree = new Room("[Your Parent's Lake House, Entryway]", "You are standing in the entryway.");
        roomTwo.getAdjacentRooms().put(Direction.w, roomOne);
        roomOne.getAdjacentRooms().put(Direction.e, roomTwo);
        roomOne.getAdjacentRooms().put(Direction.n, roomThree);
        roomThree.getAdjacentRooms().put(Direction.s, roomOne);
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
