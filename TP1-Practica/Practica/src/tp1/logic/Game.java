package tp1.logic;
import java.io.FileOutputStream;
import java.io.IOException;
import tp1.exceptions.GameLoadException;
import tp1.exceptions.GameSaveException;
import tp1.exceptions.OffBoardException;
import tp1.logic.Interfaces.GameConfiguration;
import tp1.logic.Interfaces.GameModel;
import tp1.logic.Interfaces.GameStatus;
import tp1.logic.Interfaces.GameWorld;
import tp1.logic.gameobjects.*;
import tp1.logic.lemmingRoles.*;
import tp1.view.Messages;


public class Game implements GameModel, GameStatus, GameWorld {

	private static final int LEMMINGS_TO_WIN = 2;
	public static final int DIM_X = 10;
	public static final int DIM_Y = 10;
	public static final int MIN_LEVEL = 0;
	public static final int MAX_LEVEL = 3;


	public GameObjectContainer gameObjects;
	private int level;
	private int cycle;
	private int lemmingsToWin;
	private boolean exit;
	private int numLemmings;
	private int	deadLemmings;
	private int exitLemmings;
	private GameConfiguration configuration;

	// Constructores
	// Constructor por defecto
	/*public Game() {
		gameObjects = new GameObjectContainer();
		//initObjects();
		level = 0;
		initBoard(0);
		cycle = 0;
		lemmingsToWin = LEMMINGS_TO_WIN;
		exit = false;
		numLemmings = 0;
		deadLemmings = 0;
		exitLemmings = 0;
		configuration = FileGameConfiguration.NONE;
	}*/

	// Constructor con parametros de nivel
	public Game(int nivel) {
		gameObjects = new GameObjectContainer();
		level = nivel;
		initConfiguration(nivel);
		cycle = 0;
		lemmingsToWin = LEMMINGS_TO_WIN;
		exit = false;
		deadLemmings = 0;
		exitLemmings = 0;
	}

		private void initConfiguration(int nLevel)  {
			configuration = new GameLevelConfiguration(nLevel, this);
			setConfiguration();
		}

	// Metodos de GameWorld********************************************************************************
		// Funcion para saber si en la posicion de debajo hay una pared
		public boolean isInAir(Position pos) {
			return !gameObjects.wallAtPosition(pos.nextPosition(Direction.DOWN));
		}

	// Metodos de GameStatus********************************************************************************
		// Funcion para obtener el ciclo
		public int getCycle() {
			return cycle;
		}
		// Funcion para obtener el nivel
		public int getLevel() {
			return level;
		}

		// Funcion para obtener el numero de lemmings en el tablero
		public int numLemmingsInBoard() {
			return numLemmings;
		}

		// Funcion para obtener el numero de lemmings muertos
		public int numLemmingsDead() {
			return deadLemmings;
		}

		// Funcion para obtener el numero de lemmings que han salido por la puerta
		public int numLemmingsExit() {
			return exitLemmings;
		}

		// Funcion para obtener el numero de lemmings que tienen que salir por la puerta para ganar
		public int numLemmingsToWin() {
			return lemmingsToWin;
		}

		// Funcion para obtener el numero de lemmings que faltan por salir por la puerta para ganar
		public int numLemmingsLeftToWin() {
			return lemmingsToWin - exitLemmings;
		}

		// Devuelve la representación en forma de string del objeto en la posición dada
		public String positionToString(int col, int row) {	
			return gameObjects.positionToString(col, row);
		}

	// Metodos de GameModel********************************************************************************
		// Funcion para saber si se ha acabado el juego
		public boolean isFinished() {
			return playerWins() || playerLooses() || exit;
		}

		// Funcion para ejecutar un ciclo
		public void update() {
			//Eliminamos los objetos muertos, ya sea porque los lemmings han muerto o han llegado a la puerta
			// O un rol de excavador ha eliminado una pared
			// Actualizamos los objetos
			gameObjects.update();
			increaseCycle();	
			gameObjects.removeDeadObjects();
		}
		

		public void increaseCycle(){
			cycle++;
		}

		// Funcion para saber si el jugador ha ganado
		public boolean playerWins() {
			return exitLemmings >= lemmingsToWin && numLemmings == 0;
		}
	
		// Funcion para saber si el jugador ha perdido
		public boolean playerLooses() {
			//return numLemmings + exitLemmings < lemmingsToWin;
			return numLemmings == 0;
		}

		// Funcion para salir del juego
		public void exit() {
			this.exit = true;
		}

		// Funcion que añade 1 a los lemmings que han salido por la puerta
		public void addExitLemmings(){
			exitLemmings++;
			decreaseNumLemmings();
		}

		// Funcion que decrementa el numero de lemmings
		public void decreaseNumLemmings(){
			numLemmings--;
		}

		// Funcion que añade 1 a los lemmings muertos
		public void addDeadLemmings(){
			deadLemmings++;
			decreaseNumLemmings();
		}

		public boolean positionInLimits(Position pos){
			return !leavingTheBoard(pos) && !crashingIntoLimits(pos);
		}

		// Funcion que devuelve si la posicion esta dentro de los limites del tablero
		public boolean leavingTheBoard(Position pos){
			return !pos.insideRowsLimits(DIM_Y);
		}

		// Funcion que devuelve si la posicion esta chocando con los limites del tablero
		public boolean crashingIntoLimits(Position pos){
			return !pos.insideColsLimits(DIM_X);
		}

		// Funcion que elimina un objeto del tablero
		public void removeGameObject(GameObject gameObject){
			gameObjects.remove(gameObject);
		}

		// Funcion que fija el rol de un lemming en una posicion, y devuelve si se ha podido fijar
		public boolean setRole(LemmingRole role, Position pos, String roleName) throws OffBoardException {
			if(!positionInLimits(pos)){ 
				throw new OffBoardException(Messages.COMMAND_SET_ROLE_INVALID_POSITION.formatted(pos.toString()));
			}
			return gameObjects.setRole(role, pos, roleName);
		}

		// Funcion que devuelve si hay una interaccion entre objetos, reaccionando a la interaccion si la hay
		public boolean receiveInteractionsFrom(GameItem obj){
			return gameObjects.receiveInteractionsFrom(obj);
		}
		
		// Funcion que devuelve si un nivel existe
		public boolean existsLevel(int l) {
			return l >= MIN_LEVEL && l <= MAX_LEVEL;
		}

		// Funcion para cargar un juego
		@Override
		public void load(String fileName) throws GameLoadException {
			// Intentamos cargar el juego
			try {
				configuration = new FileGameConfiguration(fileName, this);
				setConfiguration();
			} // Si no se puede cargar el juego, lanzamos una excepcion
			catch (GameLoadException e) {
				throw e;
			}
		}

		// Funcion para guardar un juego
		@Override
		public void save(String fileName) throws GameSaveException {
			// Intentamos guardar el juego
			try {
				//configuration.save(fileName);
				saveFile(fileName);
			} // Si no se puede guardar el juego, lanzamos una excepcion
			catch (GameSaveException e) {
				throw e;
			}
		}

		 public void saveFile(String fileName) throws GameSaveException {
        // Guardamos la configuración del juego en un archivo
         try (FileOutputStream stream = new FileOutputStream(fileName)){
            String auxLine;
			// Guardamos la configuración del juego
            auxLine = cycle + " " + numLemmings + " " + deadLemmings + " " + exitLemmings + " " + LEMMINGS_TO_WIN + "\n";
            // Guardamos los objetos del juego
			auxLine += gameObjects.toString();
            stream.write(auxLine.getBytes());
        } catch (IOException e) {
            throw new GameSaveException(Messages.FILE_NOT_FOUND.formatted(fileName));
        	}
    	}

		// Funcion para reiniciar el juego
		public void reset(int level) {
			// Reset estandar
			// Si el comando de reset ha sido simplemente poner "reset"
			if (level == -1) {
				//gameObjects = new GameObjectContainer();
				setConfiguration();
			// Si el comando de reset ha incluido un nivel : "reset X"
			} else {
				// Reset con configuracion
				configuration = new GameLevelConfiguration(level, this);
				setConfiguration();
			}
		}

		// Funcion para resetear la configuracion del juego
		@Override
		public void resetToDefaultConfiguration() {
			this.configuration = FileGameConfiguration.NONE;
		}

		// Funcion setter la configuracion de un juego a partir de un archivo
		@Override
		public void setConfiguration() {
			if(configuration != null) {
				this.cycle = configuration.getCycle();
				this.numLemmings = configuration.numLemmingInBoard();
				this.deadLemmings = configuration.numLemmingsDead();
				this.exitLemmings = configuration.numLemmingsExit();
				this.lemmingsToWin = configuration.numLemmingsToWin();
				this.gameObjects = configuration.getGameObjects();
				this.level = -1;
			}
		}

}