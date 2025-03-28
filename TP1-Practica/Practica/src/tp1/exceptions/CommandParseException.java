package tp1.exceptions;

public class CommandParseException extends CommandException {

    // Constructor de la clase CommandParseException
    public CommandParseException() {
        super();
    }

    public CommandParseException(String message) {
        super(message);
    }

    public CommandParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandParseException(Throwable cause) {
        super(cause);
    }

}
