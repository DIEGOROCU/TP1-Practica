package tp1.logic;

import java.io.FileOutputStream;
import java.io.IOException;
import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.GameSaveException;
import tp1.logic.Interfaces.GameConfiguration;
import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.MetalWall;
import tp1.logic.gameobjects.Wall;
import tp1.logic.lemmingRoles.ParachuterRole;
import tp1.view.Messages;


public class GameLevelConfiguration implements GameConfiguration {
    
    //Atributos
	private int cycle;
	private int level;
    private int numLemmingInBoard;
    private int numLemmingsDead;
    private int numLemmingsExit;
    private int numLemmingsToWin;
    private GameObjectContainer gameObjects;

	// Constante
	private static final int LEMMINGS_TO_WIN = 2;
	

    //Constructores
	// Constructor por defecto
	public GameLevelConfiguration(){}
	
    // Constructor por defecto (nivel 0)
	/*public GameLevelConfiguration(Game game) throws CommandExecuteException {
		cycle = 0;
		numLemmingInBoard = 0;
		numLemmingsDead = 0;
		numLemmingsExit = 0;
		numLemmingsToWin = LEMMINGS_TO_WIN;
		gameObjects = new GameObjectContainer();
        InitLevel(game.getLevel(), game);
    }*/

    // Constructor con nivel
    public GameLevelConfiguration(int level, Game game) {
		cycle = 0;
		numLemmingInBoard = 0;
		numLemmingsDead = 0;
		numLemmingsExit = 0;
		numLemmingsToWin = LEMMINGS_TO_WIN;
		gameObjects = new GameObjectContainer();
		switch(level){
			case 0:
				InitLevel(0, game);
				break;
			case 1:
				InitLevel(1, game);
				break;
			case 2:
				InitLevel(2, game);
				break;
			case 3:
				InitLevel(3, game);
				break;
			default:
				break;
		}

    }

	// Funcion que clone la configuración del nivel
	public GameLevelConfiguration clone(Game game) throws CommandExecuteException {
		GameLevelConfiguration duplicated = new GameLevelConfiguration();
		duplicated.cycle = this.cycle;
		duplicated.numLemmingInBoard = this.numLemmingInBoard;
		duplicated.numLemmingsDead = this.numLemmingsDead;
		duplicated.numLemmingsExit = this.numLemmingsExit;
		duplicated.numLemmingsToWin = this.numLemmingsToWin;
		duplicated.gameObjects = this.gameObjects.clone();
		return duplicated;
	}

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

    // Funcion para inicializar los objetos del juego dependiendo del nivel
	public void InitLevel(int level, Game game) {

		switch (level) {
			// Nivel 1
			case 0:
				gameObjects.add(new Wall(8, 1, game));
				gameObjects.add(new Wall(9, 1, game));


				gameObjects.add(new Wall(2, 4, game));
				gameObjects.add(new Wall(3, 4, game));
				gameObjects.add(new Wall(4, 4, game));

				gameObjects.add(new Wall(7, 5, game));

				gameObjects.add(new Wall(4, 6, game));
				gameObjects.add(new Wall(5, 6, game));
				gameObjects.add(new Wall(6, 6, game));
				gameObjects.add(new Wall(7, 6, game));

				gameObjects.add(new Wall(0, 9, game));
				gameObjects.add(new Wall(1, 9, game));

				gameObjects.add(new Wall(9, 9, game));
				gameObjects.add(new Wall(8, 9, game));
				gameObjects.add(new Wall(8, 8, game));

				//InitLemmings();
				gameObjects.add(new Lemming(0, 8, game));
				gameObjects.add(new Lemming(2, 3, game));
				gameObjects.add(new Lemming(9, 0, game));
				
				//InitExitDoor();
				gameObjects.add(new ExitDoor(4, 5, game));
				
				numLemmingInBoard = 3;
				break;

			// Nivel 2
			case 1:
				gameObjects.add(new Wall(8, 1, game));
				gameObjects.add(new Wall(9, 1, game));


				gameObjects.add(new Wall(2, 4, game));
				gameObjects.add(new Wall(3, 4, game));
				gameObjects.add(new Wall(4, 4, game));

				gameObjects.add(new Wall(7, 5, game));

				gameObjects.add(new Wall(4, 6, game));
				gameObjects.add(new Wall(5, 6, game));
				gameObjects.add(new Wall(6, 6, game));
				gameObjects.add(new Wall(7, 6, game));

				gameObjects.add(new Wall(0, 9, game));
				gameObjects.add(new Wall(1, 9, game));

				gameObjects.add(new Wall(9, 9, game));
				gameObjects.add(new Wall(8, 9, game));
				gameObjects.add(new Wall(8, 8, game));

				//InitLemmings();
				gameObjects.add(new Lemming(0, 8, game));
				gameObjects.add(new Lemming(2, 3, game));
				gameObjects.add(new Lemming(9, 0, game));

				gameObjects.add(new ExitDoor(4, 5, game));

				gameObjects.add(new Lemming(3, 3, game));
				
				//InitExitDoor();
				
				numLemmingInBoard = 4;
				break;

			// Nivel 3
			case 2:
				gameObjects.add(new Wall(8, 1, game));
				gameObjects.add(new Wall(9, 1, game));


				gameObjects.add(new Wall(2, 4, game));
				gameObjects.add(new Wall(3, 4, game));
				gameObjects.add(new Wall(4, 4, game));

				gameObjects.add(new Wall(7, 5, game));

				gameObjects.add(new Wall(4, 6, game));
				gameObjects.add(new Wall(5, 6, game));
				gameObjects.add(new Wall(6, 6, game));
				gameObjects.add(new Wall(7, 6, game));

				gameObjects.add(new Wall(0, 9, game));
				gameObjects.add(new Wall(1, 9, game));

				gameObjects.add(new Wall(9, 9, game));
				gameObjects.add(new Wall(8, 9, game));
				gameObjects.add(new Wall(8, 8, game));
				gameObjects.add(new Wall(3, 5, game));
				gameObjects.add(new MetalWall(3, 6, game));

				// InitLemmings();
				gameObjects.add(new Lemming(0, 8, game));
				gameObjects.add(new Lemming(2, 3, game));
				gameObjects.add(new Lemming(9, 0, game));

				gameObjects.add(new ExitDoor(4, 5, game));

				gameObjects.add(new Lemming(3, 3, game));
				gameObjects.add(new Lemming(6, 0, game));
				gameObjects.add(new Lemming(6, 0, game, new ParachuterRole()));
				
				// InitExitDoor();

				numLemmingInBoard = 6;
				break;

			case 3:
				gameObjects.add(new Wall(8, 1, game));
				gameObjects.add(new Wall(9, 1, game));


				gameObjects.add(new Wall(2, 4, game));
				gameObjects.add(new Wall(3, 4, game));
				gameObjects.add(new Wall(4, 4, game));

				gameObjects.add(new Wall(7, 5, game));

				gameObjects.add(new Wall(4, 6, game));
				gameObjects.add(new Wall(5, 6, game));
				gameObjects.add(new Wall(6, 6, game));
				gameObjects.add(new Wall(7, 6, game));

				gameObjects.add(new Wall(0, 9, game));
				gameObjects.add(new Wall(1, 9, game));

				gameObjects.add(new Wall(9, 9, game));
				gameObjects.add(new Wall(8, 9, game));
				gameObjects.add(new Wall(8, 8, game));

				//InitLemmings();
				gameObjects.add(new Lemming(9, 0, game));
				numLemmingInBoard = 1;

				gameObjects.add(new ExitDoor(4, 5, game));
			default:
                break;
		}

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
