package tp1.logic.gameobjects;

import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.logic.Interfaces.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class MetalWall extends GameObject {

    private static final String NAME = Messages.METAL_WALL_NAME;
	private static final String SHORTCUT = Messages.METAL_WALL_SHORTCUT;

    // Constructores
        // Constructor con parametros de posicion (objeto)
        public MetalWall(Position pos, boolean vivo, GameWorld gameWorld) {
            super(pos, vivo, gameWorld);
        }

        public MetalWall(Position pos, GameWorld gameWorld) {
            super(pos, true, gameWorld);
        }

        // Constructor con parametros de posicion (objeto)
        public MetalWall(int col, int row, GameWorld gameWorld) {
            super(new Position(col, row), true, gameWorld);
        }

        public MetalWall(){
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
            return Messages.METALWALL;
        }

        // Funcion para saber si una puerta es solida
        @Override
        public boolean isSolid() {
            return true;
        }

        // Funcion para interactuar con un objeto del juego
        @Override
        public boolean receiveInteraction(GameItem item) {
        	return item.interactWith(this);
        }

        @Override 
        public GameObject parse(String[] line, GameWorld game) throws ObjectParseException, OffBoardException{
            // Si el array es mayor que 2, no puede ser una puerta
            // Si el primer elemento del array es distinto del shortcut o del nombre, tampoco puede ser un muro de mtetal
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
            return new MetalWall(pos, game);
        }

        // Funcion para clonar un muro de metal
        @Override
        public MetalWall clone(){
            Position posDuplicated = pos.clone();
            GameWorld gameWorldDuplicated = this.gameWorld;
            boolean vivoDuplicated = this.vivo;
            return new MetalWall(posDuplicated, vivoDuplicated, gameWorldDuplicated);
        }

        // Funcion para guardar la representacion de una pared de metal en un archivo
        @Override
        public String toSaveString(){
            String aux;
            aux = pos.toString() + " MetalWall";
            return aux;
        }
}
