package valhalla;

public enum Articles {
    the,
    a,
    an,
    some,
    go,
    move;

    public static boolean has(String name) {
        try {
         Articles.valueOf(name);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}
