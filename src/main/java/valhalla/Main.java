package valhalla;

import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        Room roomOne = initRooms();
        Scanner scan = new Scanner(System.in);
        Player player = new Player(roomOne);
        System.out.println(player.getCurrentRoom().printRoom());
        while (true) {
            String s = scan.next();
            String response = player.doAction(s);
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



}
