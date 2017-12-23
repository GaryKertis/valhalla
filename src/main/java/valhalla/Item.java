package valhalla;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static valhalla.Constants.*;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Item {
   private String name;
   private boolean isGettable = false;
   private ActionMap actionMap;

   public Item(String name) {
       this.name = name;
       this.actionMap = new ActionMap(name);
   }

    public Item(String name, String description) {
        this.name = name;
        this.actionMap = new ActionMap(name);
        actionMap.addAction(look, description);
    }

    public Item(String name, String description, boolean isGettable) {
        this.name = name;
        this.actionMap = new ActionMap(name);
        actionMap.addAction(look, description);
        this.isGettable = isGettable;
        if (isGettable) {
            actionMap.addAction(get, actionMap.decorate(ActionMap.DEFAULT_TAKE, name));
        }
    }

   public String doAction(Command command) {
      return actionMap.doAction(command);
   }
}
