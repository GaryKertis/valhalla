import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import valhalla.*;

import static org.mockito.Mockito.*;

public class ConstructionTest {

    @Test
    public void equals() throws BadCommandException {
        Item key = new Item("key");
        assert(key.equals(new Item("key")));
    }

}
