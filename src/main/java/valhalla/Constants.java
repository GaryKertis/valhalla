package valhalla;

public class Constants {
    public static final Command look = new Command("look", false);
    public static final Command get = new Command("get", true);
    public static final Command take = new Command("take", true, get);
    public static final Command move = new Command("move", true);
    public static final Command go = new Command("go", true, move);
    public static final Command use = new Command("use", true);
    public static final Command give = new Command("give", true, use);
    public static final Command put = new Command("put", true, use);

    // valid movements
    public static final Command east = new Command("east", false);
    public static final Command west = new Command("west", false);
    public static final Command south = new Command("south", false);
    public static final Command north = new Command("north", false);
    public static final Command up = new Command("up", false);
    public static final Command down = new Command("down", false);
    public static final Command southeast = new Command("southeast", false);
    public static final Command southwest = new Command("southwest", false);
    public static final Command northeast = new Command("northeast", false);
    public static final Command northwest = new Command("northwest", false);
    public static final Command e = new Command("e", false, east);
    public static final Command w = new Command("w", false, west);
    public static final Command s = new Command("s", false, south);
    public static final Command n = new Command("n", false, north);
    public static final Command u = new Command("u", false, up);
    public static final Command d = new Command("d", false, down);
    public static final Command se = new Command("se", false, southeast);
    public static final Command sw = new Command("sw", false, southwest);
    public static final Command ne = new Command("ne", false, northeast);
    public static final Command nw = new Command("nw", false, northwest);

    public static final Item key = new Item("key");


}
