import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import valhalla.*;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;

public class CommandParserTest {

    Player mockedPlayer = mock(Player.class);
    CommandParser parser = new CommandParser(mockedPlayer);
    Item mockedKey = mock(Item.class);
    Item mockedWindow = mock(Item.class);

    @Before
    public void setup() {
        when(mockedKey.getName()).thenReturn("key");
        when(mockedWindow.getName()).thenReturn("window");
        when(mockedPlayer.getItem("door")).thenThrow(BadCommandException.class);
        when(mockedPlayer.getItem("key")).thenReturn(mockedKey);
        when(mockedPlayer.getItem("window")).thenReturn(mockedWindow);
    }

    @Test(expected = BadCommandException.class)
    public void noObject() throws BadCommandException {
        parser.parseCommand("give");
    }

    @Test
    public void leadingSpaces() throws BadCommandException {
        parser.parseCommand(" east");
    }

    @Test
    public void trailingSpaces() throws BadCommandException {
        parser.parseCommand("east  ");
    }

    @Test
    public void padding() throws BadCommandException {
        parser.parseCommand("  east  ");
    }

    @Test(expected = BadCommandException.class)
    public void notInList() throws BadCommandException {
        parser.parseCommand("fake");
    }

    @Test(expected = BadCommandException.class)
    public void danglingPreposition() throws BadCommandException {
        parser.parseCommand("move under");
    }

    @Test
    public void validConstruction() throws BadCommandException {
        Construction test = parser.parseCommand("take key");
        Construction expected = new Construction(CommandList.GET.getCommand(), mockedKey);
        assertEquals(test, expected);
    }

    @Test
    public void validConstruction2() throws BadCommandException {
        Construction test = parser.parseCommand("take key under window");
        Construction expected = new Construction(CommandList.GET.getCommand(), mockedKey, Preposition.UNDER,
                mockedWindow);
        assertEquals(test, expected);
    }

    @Test
    public void validConstruction3() throws BadCommandException {
        Construction test = parser.parseCommand("look");
        Construction expected = new Construction(CommandList.LOOK.getCommand());
        assertEquals(test, expected);
    }

    @Test(expected = BadCommandException.class)
    public void itemNotFound() throws BadCommandException {
        parser.parseCommand("take door");
    }

    @Test(expected = BadCommandException.class)
    public void danglingPrepositionWithUnfoundObject() throws BadCommandException {
        parser.parseCommand("take door under");
    }

    @Test(expected = BadCommandException.class)
    public void danglingPrepositionWithFoundObject() throws BadCommandException {
        parser.parseCommand("take key under");
    }

    @Test(expected = BadCommandException.class)
    public void secondItemNotFound() throws BadCommandException {
        parser.parseCommand("take key under door");
    }

    @Test(expected = BadCommandException.class)
    public void twoItems() throws BadCommandException {
        parser.parseCommand("take key door");
    }

    @Test(expected = BadCommandException.class)
    public void twoVerbs() throws BadCommandException {
        parser.parseCommand("take key take");
    }

    @Test()
    public void validShortConstruction() throws BadCommandException {
        parser.parseCommand("take key");
    }

    @Test(expected = BadCommandException.class)
    public void multiplePrepositions() throws BadCommandException {
        parser.parseCommand("look over item over");
    }

    @Test(expected = BadCommandException.class)
    public void doubleObject() throws BadCommandException {
        parser.parseCommand("look key window");
    }

    @Test(expected = BadCommandException.class)
    public void missingPreposition() throws BadCommandException {
        parser.parseCommand("look key over");
    }

    @Test()
    public void validLongConstruction() throws BadCommandException {
        Construction expected = new Construction(CommandList.GET.getCommand(), mockedKey, Preposition.ON, mockedWindow);
        Construction test = parser.parseCommand("get key on window");
        assertEquals(expected, test);

    }

    @Test
    public void stripUnnecessaryWordsTestRemoveArticle() {
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("abba", "dabba"));
        ArrayList<String> result = parser.stripUnnecessaryWords(new ArrayList<>(Arrays.asList("abba", "dabba", "the")));
        assert (expected.equals(result));
    }

    @Test
    public void stripUnnecessaryWordsTestRemoveAdjective() {
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("abba", "knee"));
        ArrayList<String> result = parser.stripUnnecessaryWords(new ArrayList<>(Arrays.asList("abba", "strange",
                "knee")));
        assert (expected.equals(result));
    }

}
