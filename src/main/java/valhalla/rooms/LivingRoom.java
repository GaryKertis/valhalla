package valhalla.rooms;

import lombok.Data;
import valhalla.*;

@Data
public class LivingRoom extends Room {

    public LivingRoom() {
        super();
        this.setTitle("Your Parent's House, Living Room");
        this.setDescription("You are standing in the living room of your parent's lake house. Late morning " +
                "sunlight filters through the windows. The TV quietly hums. The odor of mildew and scented candle is " +
                "in " + "the air. There is a faded painting above the couch and a bag of cheetos resting on the " +
                "coffee table.");

        this.addItem(new Item("tv", "Judge Judd is on. He is verbally abusing a simple person."));
        this.addItem(new Item("table", "You see an open bag of cheetos. There are some magazines that showcase your"
                + " father's intellectual pretensions. The cover story of economist is on the unemployment epidemic."));

        this.addItem(new Item("cheetos", "Yum. The puffy ones, not the crunchy ones."));

    }

}
