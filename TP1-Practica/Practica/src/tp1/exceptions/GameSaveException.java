package tp1.exceptions;

public class GameSaveException extends GameModelException {

    public GameSaveException() {
        super();
    }

    public GameSaveException(String message) {
        super(message);
    }

    public GameSaveException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameSaveException(Throwable cause) {
        super(cause);
    }
}