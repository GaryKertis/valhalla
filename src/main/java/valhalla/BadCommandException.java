package valhalla;

public class BadCommandException extends Exception {
    BadCommandException(String message) {
        super(message);
    }
    BadCommandException() {
        super();
    }
}
