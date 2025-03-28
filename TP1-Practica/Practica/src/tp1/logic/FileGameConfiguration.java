package tp1.logic;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import tp1.exceptions.GameLoadException;
import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.logic.Interfaces.GameConfiguration;
import tp1.logic.Interfaces.GameWorld;
import tp1.logic.gameobjects.GameObjectFactory;
import tp1.view.Messages;

public class FileGameConfiguration implements GameConfiguration {

    //Atributos
    private int cycle;
    private int numLemmingInBoard;
    private int numLemmingsDead;
    private int numLemmingsExit;
    private int numLemmingsToWin;
    private int level;
    private GameObjectContainer gameObjects;

    //Constantes
    public static final GameConfiguration NONE = new FileGameConfiguration();

    //Constructores
    public FileGameConfiguration() {
    }

    // Funcion que clona la configuración del juego del fichero
    public FileGameConfiguration clone(){
        FileGameConfiguration duplicated = new FileGameConfiguration();
        duplicated.cycle = this.cycle;
        duplicated.numLemmingInBoard = this.numLemmingInBoard;
        duplicated.numLemmingsDead = this.numLemmingsDead;
        duplicated.numLemmingsExit = this.numLemmingsExit;
        duplicated.numLemmingsToWin = this.numLemmingsToWin;
        duplicated.level = this.level;
        duplicated.gameObjects = this.gameObjects.clone();
        return duplicated;
    }

    public FileGameConfiguration(String fileName, GameWorld game) throws GameLoadException {
        // Leemos el archivo
        gameObjects = new GameObjectContainer();
        String lineaAux = null;
        try (FileReader fr = new FileReader(fileName); BufferedReader br = new BufferedReader(fr)) {
            // Leemos la configuración del juego
            lineaAux = br.readLine();
            String[] linea = lineaAux.split(" ");
            if (linea.length != 5) {
                // Lanza una excepcion con mensaje de error Incorrect game status
                throw new GameLoadException(Messages.FILE_FORMAT_ERROR.formatted(lineaAux));
            }
            // Actualizamos los valores de la configuración del juego
            cycle = Integer.parseInt(linea[0]);
            numLemmingInBoard = Integer.parseInt(linea[1]);
            numLemmingsDead = Integer.parseInt(linea[2]);
            numLemmingsExit = Integer.parseInt(linea[3]);
            numLemmingsToWin = Integer.parseInt(linea[4]);
            level = -1;
            // Leemos los objetos del juego
            while ((lineaAux = br.readLine()) != null) {
                gameObjects.add(GameObjectFactory.parse(lineaAux, game));
            }
        } catch (IOException e) {
            // Lanza una excepcion con mensaje de error: Filenot found si no encuentra el archivo
            throw new GameLoadException(Messages.FILE_NOT_FOUND.formatted(fileName));
        } catch (ObjectParseException e) {
            // Lanza una excepcion si el gameObject no es reconocido
            throw new GameLoadException(e.getMessage().formatted(lineaAux));
        } catch (OffBoardException e) {
            // Lanza una excepcion con mensaje de error: Off board exception
            throw new GameLoadException(e.getMessage().formatted(lineaAux));
        }
    }

    // Clonamos el contenedor de objetos
    @Override
    public GameObjectContainer getGameObjects() {
        return gameObjects.clone();
    }

    // Getters
    @Override
    public int getCycle() {
        return cycle;
    }
    @Override
    public int numLemmingInBoard() {
        return numLemmingInBoard;
    }
    @Override
    public int numLemmingsDead() {
        return numLemmingsDead;
    }
    @Override
    public int numLemmingsExit() {
        return numLemmingsExit;
    }
    @Override
    public int numLemmingsToWin() {
        return numLemmingsToWin;
    }

    // Funcion que guarda la configuración del juego en un archivo
    /*@Override
    public void save(String fileName) throws GameSaveException {
        // Guardamos la configuración del juego en un archivo
         try {
            FileOutputStream stream = new FileOutputStream(fileName);
            String auxLine;
            auxLine = cycle + " " + numLemmingInBoard + " " + numLemmingsDead + " " + numLemmingsExit + " " + numLemmingsToWin + "\n";
            auxLine += gameObjects.toString();
            stream.write(auxLine.getBytes());
            stream.close();
        } catch (IOException e) {
            throw new GameSaveException(Messages.FILE_NOT_FOUND.formatted(fileName));
        }
    }*/

}



