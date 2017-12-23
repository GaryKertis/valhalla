import org.junit.Test;

import valhalla.*;
import valhalla.ActionMap;

import static valhalla.Constants.*;

public class ItemTest {
    @Test
    public void equals() throws BadCommandException {
        Item key = new Item("key");
        assert(key.equals(new Item("key")));
    }

    @Test
    public void noDescription() throws BadCommandException {
        Item key = new Item("key");
        assert(key.doAction(look).equals(String.format(ActionMap.DEFAULT_LOOK, "key")));
    }

    @Test
    public void withDescription() {
        String desc = "An old rusty key";
        Item key = new Item("key", desc);
        assert(key.doAction(look).equals(desc));
    }

}
