package tp1.exceptions;

public class CommandException extends Exception {
    
    // Constructor de la clase CommandException
    public CommandException() {
        super();
    }
    
    public CommandException(String message) {
        super(message);
    }
    
    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public CommandException(Throwable cause) {
        super(cause);
    }

}
