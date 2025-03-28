package tp1.exceptions;

public class ObjectParseException extends GameParseException {
    public ObjectParseException() {
        super();
    }

    public ObjectParseException(String message) {
        super(message);
    }

    public ObjectParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectParseException(Throwable cause) {
        super(cause);
    }
}