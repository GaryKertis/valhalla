package valhalla;

public enum Adjectives {
    strange,
    coffee;

    public static boolean has(String name) {
        try {
            Adjectives.valueOf(name);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}
