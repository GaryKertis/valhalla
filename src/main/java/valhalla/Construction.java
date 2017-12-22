package valhalla;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Construction {

    private Command command;
    private Preposition preposition;
    private Item item1;
    private Item item2;
    private int type;

    public Construction(Command command) {
        this.command = command;
    }

    public Construction(Command command, Item item) {
        this.command = command;
        this.item1 = item;
    }

    public Construction(Command command, Preposition preposition, Item item) {
        this.command = command;
        this.preposition = preposition;
        this.item1 = item;
    }

    public Construction(Command command, Item item1, Preposition preposition, Item item2) {
        this.command = command;
        this.preposition = preposition;
        this.item1 = item1;
        this.item2 = item2;
    }

    public String run() {
        if (this.getItem1() == null) {
            return command.getDefaultText();
        }
        return item1.doAction(command);
    }
}
