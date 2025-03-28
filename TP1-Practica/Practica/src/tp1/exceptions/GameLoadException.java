package tp1.exceptions;

public class GameLoadException extends GameModelException {
    public GameLoadException() {
        super();
    }

    public GameLoadException(String message) {
        super(message);
    }

    public GameLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameLoadException(Throwable cause) {
        super(cause);
    }
    
}
