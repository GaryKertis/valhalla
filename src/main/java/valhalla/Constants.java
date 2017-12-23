package valhalla;

public class Constants {
    public static final Command look = new Command("look", false);
    public static final Command get = new Command("get", true);
    public static final Command use = new Command("use", true);
    public static final Command inventory = new Command("inventory", false);
    public static final Command take = new Command("take", get);
    public static final Command give = new Command("give", use);
    public static final Command put = new Command("put", use);
    public static final Command i = new Command("i", inventory);

    // valid movements
    public static final Movement east = new Movement("east", false, Direction.e);
    public static final Movement west = new Movement("west", false, Direction.w);
    public static final Movement south = new Movement("south", false, Direction.s);
    public static final Movement north = new Movement("north", false, Direction.n);
    public static final Movement up = new Movement("up", false, Direction.u);
    public static final Movement down = new Movement("down", false, Direction.d);
    public static final Movement southeast = new Movement("southeast", false, Direction.se);
    public static final Movement southwest = new Movement("southwest", false, Direction.sw);
    public static final Movement northeast = new Movement("northeast", false, Direction.ne);
    public static final Movement northwest = new Movement("northwest", false, Direction.nw);
    public static final Movement e = new Movement("e", false, east);
    public static final Movement w = new Movement("w", false, west);
    public static final Movement s = new Movement("s", false, south);
    public static final Movement n = new Movement("n", false, north);
    public static final Movement u = new Movement("u", false, up);
    public static final Movement d = new Movement("d", false, down);
    public static final Movement se = new Movement("se", false, southeast);
    public static final Movement sw = new Movement("sw", false, southwest);
    public static final Movement ne = new Movement("ne", false, northeast);
    public static final Movement nw = new Movement("nw", false, northwest);

    public static final Item key = new Item("key");


}
