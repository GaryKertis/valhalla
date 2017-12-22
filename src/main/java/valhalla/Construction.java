package valhalla;

public class Construction {

    private Command command;
    private Preposition preposition;
    private Item item1;
    private Item item2;

    Construction(Command command) {
        this.command = command;
    }

    Construction(Command command, Preposition preposition, Item item) {
        this.command = command;
        this.preposition = preposition;
        this.item1 = item;
    }

    Construction(Command command, Item item1, Preposition preposition, Item item2) {
        this.command = command;
        this.preposition = preposition;
        this.item1 = item1;
        this.item2 = item2;
    }

}
