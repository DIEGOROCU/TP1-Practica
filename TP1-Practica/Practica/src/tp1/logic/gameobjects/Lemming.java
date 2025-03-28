package tp1.logic.gameobjects;
import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.exceptions.RoleParseException;
import tp1.logic.Direction;
import tp1.logic.Interfaces.GameWorld;
import tp1.logic.Position;
import tp1.logic.lemmingRoles.*;
import tp1.view.Messages;


public class Lemming extends GameObject{

	private static final String NAME = Messages.LEMMING_NAME;
	private static final String SHORTCUT = Messages.LEMMING_SHORTCUT;

	//Atributos
	private Direction direction;
	private int fuerzaCaida; // Con 3 se muere
	private LemmingRole role;
	private boolean isInAir = false;
	
	// Constructores
	//Constructor por defecto
	public Lemming(){
		super();
		direction = Direction.RIGHT;
		fuerzaCaida = 0;
		role = new WalkerRole();
		isInAir = false;
	}

	public Lemming(int col, int row, GameWorld GameWorld) {
		super(new Position(col, row), true, GameWorld);
		direction = Direction.RIGHT;
		fuerzaCaida = 0;
		role = new WalkerRole();
		isInAir = false;
	}
	
	//Constructor con parametros
	public Lemming(int col, int row, GameWorld GameWorld, LemmingRole role) {
		super(new Position(col, row), true, GameWorld);
		direction = Direction.RIGHT;
		fuerzaCaida = 0;
		this.role = role;
		isInAir = false;
	}

	public Lemming(Position pos, Direction dir, int fC, LemmingRole role, GameWorld GameWorld) {
		super(pos, true, GameWorld);
		direction = dir;
		fuerzaCaida = fC;
		this.role = role;
		isInAir = false;
	}


	//FUNCIONES ABSTRACTAS DE LA CLASE GameWorldOBJECT
		// Funcion para obtener el icono del lemming
		@Override
		public String toString(){ 
			return role.getIcon(this);
		}

		// Funcion para saber si un lemming es solido
		@Override
		public boolean isSolid() { 
			return false; 
		}
		
		// Funcion para actualizar el lemming
		public void update() {
			if(vivo){ 
				isInAir = gameWorld.isInAir(pos);
				role.play(this);
			}
		}
	
	// Setters
	//Funcion para cambiar la posicion segun la direccion

	// Funcion para que un Lemming caiga y muera si se cae de la pantalla
	public void falls(){
		fuerzaCaida++;
		pos.update(Direction.DOWN);
		if(gameWorld.leavingTheBoard(pos)) {
			dies();
			gameWorld.addDeadLemmings();
		}
	}

	// Funcion para cambiar la direccion del lemming
	public void inverseDirection(){
		if (direction == Direction.RIGHT) {
			direction = Direction.LEFT;
		} else {
			direction = Direction.RIGHT;
		}
	}

	// Funcion para saber si un lemming se sale de los limites
	public boolean crashingIntoLimits(){
		return pos.nextPosition(direction).crashingIntoLimits();
	}

	// Funcion para realizar lo que ocurriria si el lemming se mueve en el eje x
	public void walk(){
		//if(gameWorld.wallAtPosition(pos.nextPosition(direction)) || crashingIntoLimits()) inverseDirection();
		pos.update(direction);
	}

	// Funcion para saber si se choca con una pared hacia abajo
	public boolean crashingIntoWall(Wall wall){
		return wall.isInPosition(pos.nextPosition(Direction.DOWN));
	}

	// Funcion para saber si se choca con una pared de metal hacia abajo
	public boolean crashingIntoWall(MetalWall wall){
		return wall.isInPosition(pos.nextPosition(Direction.DOWN));
	}

	// Funcion para saber si se choca con una pared
	public boolean bounceIntoWall(Wall wall){
		return wall.isInPosition(pos.nextPosition(direction));
	}
	
	// Funcion para saber si se choca con una pared de metal
	public boolean bounceIntoWall(MetalWall wall){
		return wall.isInPosition(pos.nextPosition(direction));
	}

	// Funcion para saber si un lemming se saldra de la pantalla
	public boolean fallOutOfTheWorld(){
		return gameWorld.leavingTheBoard(pos.nextPosition(Direction.DOWN));
	}
	
	// Funcion para saber si un lemming ha caido demasiado (3 o mas)
	public boolean tooKinectEnergy() {
		return fuerzaCaida >= 3;
	}

	// Funcion que aumenta el numero de lemmings muertos
	public void addDeadLemmings(){
		gameWorld.addDeadLemmings();
	}

	// Funcion que devuelve si un Lemming esta en el aire
	public boolean isInAir(){
		return isInAir;
	}
	
	// Mueve el lemming si es andante
	public void move() {
		if(isInAir()) falls();
		else if (fallOutOfTheWorld()) {
			dies();
			addDeadLemmings();
		}
		else if(crashingIntoLimits()) inverseDirection();
		else{ 
			walk();
			featherFall();
		}
	}

	// Getters
		//Funcion para obtener la direccion
		public Direction getDirection() {
			Direction newDirection = direction;
			return newDirection;
		}

		// Funcion para obtener el role
		public WalkerRole getRol() {
			return new WalkerRole();
		}

		// Funcion para aumentar el numero de lemmings que han salido
		public void addExitLemmings(){
			gameWorld.addExitLemmings();
		}

		// Funcion para setear el role de un lemming (devuelve si se ha podido cambiar)
		@Override
		public boolean setRole(LemmingRole role, String roleName) {
			if (this.role.matchRoleName(roleName)) return false;
			this.role = role;
			return true;
		}
		
		// Funcion para resetear la fuerza de caida
        public void featherFall() {
        	fuerzaCaida = 0;
        }

		// Funcion para desactivar el role de un lemming (walkerRole)
		public void disableRole(){
			role = new WalkerRole();
			//role.play(this);
		}

		// Funcion para recibir interacciones de un item del juego
		@Override
        public boolean receiveInteraction(GameItem item) {
        	return item.interactWith(this);
        }

		// Funcion para interactuar con una pared
        @Override
        public boolean interactWith(Wall wall) {
        	return role.interactWith(wall, this);
        }

		// Funcion para interactuar con una pared de metal
		@Override
        public boolean interactWith(MetalWall metalWall) {
        	return role.interactWith(metalWall, this);
        }

		// Funcion para interactuar con cualquier cosa
		public boolean interactWithEverything() {
			return gameWorld.receiveInteractionsFrom(this);
		}

		// Funcion para interactuar con la puerta de salida
		@Override
		public boolean interactWith(ExitDoor exit){
			boolean interaction = false; 
			//Si el lemming andante llega a la puerta de salida, se va y se añade una unidad al numero de lemming que han salido
			if(isInPosition(exit)){
				dies();
				addExitLemmings();
				interaction = true;
			}
			return interaction;
		}
	
	@Override
	public GameObject parse(String[] line, GameWorld game) throws ObjectParseException, OffBoardException {
		if (line.length != 5 || !(line[1].equalsIgnoreCase(SHORTCUT) || line[1].equalsIgnoreCase(NAME))) {
			return null;
		}
		Position position; 
		try {
			position = getPositionFrom(line[0]);
		} catch (OffBoardException e) {
			throw e;
		}
		Direction dir;
		try {
			dir = getDirectionFrom(line[2]);
		} catch (ObjectParseException e) {
			// Definir mensaje de error: Invalid direction: %s
			throw e;
		}
		int fC = Integer.parseInt(line[3]);
		try {
			role = LemmingRoleFactory.parse(line[4]);
		} catch (RoleParseException e) {
			throw new ObjectParseException(Messages.INVALID_LEMMING_ROLE);
		}
		return new Lemming(position, dir, fC, role, game);
	}

	// Funcion para clonar un lemming
	@Override
	public Lemming clone(){
		Position posDuplicated = pos.clone();
		GameWorld gameWorldDuplicated = this.gameWorld;
		Direction dirDuplicated = direction;
		int fCDuplicated = fuerzaCaida;
		LemmingRole roleDuplicated = role;
		return new Lemming(posDuplicated, dirDuplicated, fCDuplicated, roleDuplicated, gameWorldDuplicated);
	}

    // Funcion para obtener la direccion de un objeto a partir de un string
    private Direction getDirectionFrom(String line) throws ObjectParseException {
        if (line.equalsIgnoreCase("RIGHT")) {
            return Direction.RIGHT;
        } else if(line.equalsIgnoreCase("LEFT")) {
            return Direction.LEFT;
        }
        else if(line.equalsIgnoreCase("UP") || line.equalsIgnoreCase("DOWN")){
            throw new ObjectParseException(Messages.INVALID_LEMMING_DIRECTION);
        }
        else {
            throw new ObjectParseException(Messages.NOT_KWNOWN_DIRECTION);
        }
    }

	/// Funcion para guardar la representacion de un lemming en un archivo
	@Override
	public String toSaveString(){
		String aux;
		aux = pos.toString() + " Lemming " + direction.toString() + " " + fuerzaCaida + " " + role.getName();
		return aux;
	}
}