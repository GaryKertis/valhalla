package valhalla;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import valhalla.rooms.ActionMap;

import java.util.HashMap;
import java.util.Optional;

import static valhalla.Constants.*;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Item {
   private String name;
   private ActionMap actionMap = new ActionMap();

   public Item(String name) {
      this.name = name;
   }

    public Item(String name, String description) {
        this.name = name;
        actionMap.addAction(look, description);
    }

   public String doAction(Command command) {
      return actionMap.doAction(command);
   }
}
