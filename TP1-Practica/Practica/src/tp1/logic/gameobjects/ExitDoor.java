package tp1.logic.gameobjects;

import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.logic.Interfaces.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class ExitDoor extends GameObject {

    private static final String NAME = Messages.EXIT_DOOR_NAME;
	private static final String SHORTCUT = Messages.EXIT_DOOR_SHORTCUT;


    // Constructores
        // Constructor con parametros de posicion (objeto)
        public ExitDoor(Position pos, boolean vivo, GameWorld gameWorld) {
            super(pos, vivo, gameWorld);
        }

        public ExitDoor(Position pos, GameWorld gameWorld) {
            super(pos, true, gameWorld);
        }

        // Constructor con parametros de posicion (objeto)
        public ExitDoor(int col, int row, GameWorld gameWorld) {
            super(new Position(col, row), true, gameWorld);
        }

        public ExitDoor(){
            super();
        }

    // Setters
        // Funcion update
        public void update() {
            // No hace nada
        }

        // Funcion para obtener la representacion de la puerta de salida
        @Override
        public String toString(){
            return Messages.EXIT_DOOR;
        }

        // Funcion para saber si una puerta es solida
        @Override
        public boolean isSolid() {
            return false;
        }

        // Funcion para interactuar con un objeto del juego
        @Override
        public boolean receiveInteraction(GameItem item) {
        	return item.interactWith(this);
        }

        @Override 
        public GameObject parse(String[] line, GameWorld game) throws ObjectParseException, OffBoardException{
            // Si el array es mayor que 2, no puede ser una puerta
            // Si el primer elemento del array es distinto del shortcut o del nombre, tampoco puede ser una puerta
            if(line.length > 2 || !(line[1].equalsIgnoreCase(SHORTCUT) || line[1].equalsIgnoreCase(NAME))) {
                return null;
            }
            // Si la posicion no esta en lo limites del tablero, se lanza una excepcion
            try {
                pos = getPositionFrom(line[0]);
            } catch (OffBoardException e) {
                throw e;
            }
            // Se devuelve una nueva puerta de la salida con la posicion y el juego
            return new ExitDoor(pos, game);
        }

        // Funcion para clonar una puerta de salida
        @Override
        public ExitDoor clone(){
            Position posDuplicated = pos.clone();
            GameWorld gameWorldDuplicated = this.gameWorld;
            boolean vivoDuplicated = this.vivo;
            return new ExitDoor(posDuplicated, vivoDuplicated, gameWorldDuplicated);
        }

        // Funcion para guardar la representacion de una puerta en un archivo
        @Override
        public String toSaveString(){
            String aux;
            aux = pos.toString() + " ExitDoor";
            return aux;
        }
}
