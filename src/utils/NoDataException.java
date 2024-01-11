package src.utils;

public class NoDataException extends Exception {
    public NoDataException() {
        super("No data.");
    }

    public NoDataException(String message) {
        super(message);
    }
}
