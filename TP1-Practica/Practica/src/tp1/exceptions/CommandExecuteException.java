package tp1.exceptions;
public class CommandExecuteException extends CommandException {

    // Constructor de la clase CommandExecuteException
    public CommandExecuteException() {
        super();
    }

    public CommandExecuteException(String message) {
        super(message);
    }

    public CommandExecuteException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandExecuteException(Throwable cause) {
        super(cause);
    }

}
