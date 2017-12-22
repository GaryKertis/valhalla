import org.junit.Before;
import org.junit.Test;
import valhalla.BadCommandException;
import valhalla.CommandParser;
import valhalla.Player;
import valhalla.Room;
import static org.mockito.Mockito.*;

public class CommandParserTest {

    CommandParser parser = new CommandParser();
    Player mockedPlayer = mock(Player.class);

    @Before
    public void setup() {
        when(mockedPlayer.canFindItem(anyString())).thenReturn(false);
        when(mockedPlayer.canFindItem("key")).thenReturn(true);
    }

    @Test(expected = BadCommandException.class)
    public void noObject() throws BadCommandException {
        parser.parseCommand("move", mockedPlayer);
    }

    @Test
    public void leadingSpaces() throws BadCommandException {
        parser.parseCommand(" east", mockedPlayer);
    }

    @Test
    public void trailingSpaces() throws BadCommandException {
        parser.parseCommand("east  ", mockedPlayer);
    }

    @Test
    public void padding() throws BadCommandException {
        parser.parseCommand("  east  ", mockedPlayer);
    }

    @Test(expected = BadCommandException.class)
    public void notInList() throws BadCommandException {
        parser.parseCommand("fake", mockedPlayer);
    }

    @Test(expected = BadCommandException.class)
    public void danglingPreposition() throws BadCommandException {
        parser.parseCommand("move under", mockedPlayer);
    }

    @Test(expected = BadCommandException.class)
    public void itemNotFound() throws BadCommandException {
        parser.parseCommand("move under door", mockedPlayer);
    }

    @Test(expected = BadCommandException.class)
    public void danglingPrepositionWithUnfoundObject() throws BadCommandException {
        parser.parseCommand("move door under", mockedPlayer);
    }

    @Test(expected = BadCommandException.class)
    public void danglingPrepositionWithFoundObject() throws BadCommandException {
        parser.parseCommand("move key under", mockedPlayer);
    }

    @Test(expected = BadCommandException.class)
    public void secondItemNotFound() throws BadCommandException {
        parser.parseCommand("move key under door", mockedPlayer);
    }

    @Test(expected = BadCommandException.class)
    public void twoItems() throws BadCommandException {
        parser.parseCommand("move key door", mockedPlayer);
    }

    @Test(expected = BadCommandException.class)
    public void twoVerbs() throws BadCommandException {
        parser.parseCommand("move key move", mockedPlayer);
    }

    @Test()
    public void validShortConstruction() throws BadCommandException {
        parser.parseCommand("move key", mockedPlayer);
    }

}
