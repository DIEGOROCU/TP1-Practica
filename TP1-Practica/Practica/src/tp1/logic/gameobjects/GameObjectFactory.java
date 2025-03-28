package tp1.logic.gameobjects;
import java.util.Arrays;
import java.util.List;
import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.logic.Interfaces.GameWorld;
import tp1.view.Messages;

// lanza ObjectParseException si line no se corresponde con ninguno
// de los objetos disponibles (todos han devuelto null)
// o alguno de ellos ha generado una excepcion
// lanza OffBoardException si la posicion en line esta fuera del tabero
// Nunca devuelve null
public class GameObjectFactory {

    private static final List<GameObject> availableGameObjects = Arrays.asList(
		new Lemming(),
		new Wall(),
        new MetalWall(),
        new ExitDoor()
	);

    public static GameObject parse(String line, GameWorld game) throws ObjectParseException, OffBoardException{
        String[] words = line.trim().split("\\s+");
        for (GameObject go: availableGameObjects) {
            try {
                GameObject gameObject = go.parse(words, game);
                if (gameObject != null) {
                    return gameObject;
                }
            } catch (ObjectParseException | OffBoardException e) {
                throw e;
            }
        }
        throw new ObjectParseException(Messages.UNKNOWN_GAME_OBJECT);
    }
}
