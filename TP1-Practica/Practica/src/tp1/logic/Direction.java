package tp1.logic;

/**
 * Represents the allowed directions in the game
 *
 */
public enum Direction {
	LEFT(-1,0), RIGHT(1,0), DOWN(0,1), UP(0,-1), NONE(0,0);
	
	private int col;
	private int row;
	
	// Constructores
		// Constructor por defecto
		private Direction() {
			col=0;
			row=0;
		}

		// Constructor con parametros
		private Direction(int c, int r) {
			col=c;
			row=r;
		}
	
	// Setters
		// Funcion para establecer una direccion
		public void setDirection(Direction dir) {
			col = dir.col;
			row = dir.row;
		}

	// Getters
		// Funcion para obtener la coordenada x
		public int getCol() {
			return col;
		}

		// Funcion para obtener la coordenada row
		public int getRow() {
			return row;
		}

		// Funcion para devolver la direccion
		public Direction getDir() {
			Direction newDirection = this;
			return newDirection;
		}	
}
