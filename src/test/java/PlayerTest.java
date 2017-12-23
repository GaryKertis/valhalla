import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import valhalla.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.mockito.Mockito.*;

public class PlayerTest {

    Player player;
    Room room;
    Item key;
    Item door;

    @Before
    public void setup() {
        room = mock(Room.class);
        player = new Player(room);
        key = new Item("key", "An old key", true);
        door = new Item("door", "A new door", true);
        when(room.getItem("key")).thenReturn(key);
        when(room.getItem("door")).thenThrow(IllegalArgumentException.class);
        when(room.getItems()).thenReturn(new ArrayList<Item>(Arrays.asList(key)));
        when(room.hasItem("door")).thenReturn(false);
        when(room.hasItem("key")).thenReturn(true);
    }

    @Test
    public void setRoom() {
        assertEquals(player.getCurrentRoom(), room);
    }

    @Test
    public void takeItem() throws BadCommandException {
        Item[] expected = {key};
        player.takeItem(key);
        assertArrayEquals(expected, player.getInventory().toArray());
    }

    @Test(expected = BadCommandException.class)
    public void cannotTakeItem() throws BadCommandException {
        player.takeItem(door);
    }

    @Test
    public void cannotFindItem() {
        assertFalse(player.canFindItem("door"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotGetItem() {
        player.getItemFromInventory("door");
    }

    @Test
    public void canGetItemFromRoom() throws BadCommandException {
        player.takeItem(key);
        assertEquals(player.getItem("key"), key);
    }

    @Test
    public void inventory() throws BadCommandException {
        player.takeItem(key);
        String result = "You are carrying:" + '\n' + "key";
        assertEquals(result, player.printInventory());
    }

    @Test(expected = BadCommandException.class)
    public void takeSameItemTwice() throws BadCommandException {
        player.takeItem(key);
        player.takeItem(key);
    }

    @Test
    public void canFindItemInRoom() {
        assert (player.canFindItem("key"));
    }

    @Test
    public void cannotFindItemNotInRoom() {
        assertFalse(player.canFindItem("wand"));
    }

}
