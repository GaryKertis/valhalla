import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import valhalla.*;

import java.util.HashMap;

import static org.mockito.Mockito.*;

public class RoomTest {

    Room roomOne;
    Room roomTwo;
    Room roomThree;
    Room roomFour;
    Item mockedKey = new Item("key");
    Item mockedDoor = new Item("door");

    @Before
    public void setup() {
        roomOne = new Room("One", "Test Description");
        roomTwo = new Room("Two", "Test Description");
        roomThree = new Room("Three", "Test Description");
        roomFour = new Room("Four", "Test Description");

        roomOne.getAdjacentRooms().put(Direction.e, roomTwo);

        roomOne.getItems().add(mockedKey);
        roomOne.getItems().add(mockedDoor);

    }

    @Test
    public void title() {
        assertEquals(roomOne.getTitle(), "One");
    }

    @Test
    public void description() {
        assertEquals(roomOne.getDescription(), "Test Description");
    }

    @Test
    public void hasItemValid() {
        assert(roomOne.hasItem("key"));
        assert(roomOne.hasItem("door"));
    }

    @Test
    public void getItemValid() {
        assertEquals(roomOne.getItem("key"), mockedKey);
        assertEquals(roomOne.getItem("door"), mockedDoor);
    }

    @Test
    public void hasItemInValid() {
        assertFalse(roomOne.hasItem("star"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getItemInValid() {
        roomOne.getItem("star");
    }

    @Test
    public void possibleDirections() {
        assertEquals(roomOne.possibleDirections(), "Possible directions: e");
    }

}
