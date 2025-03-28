package tp1.logic;

import tp1.view.Messages;

public class Position {

	// Atributos
	private int col;
	private int row;

	// Constructores
	// Constructor por defecto
	public Position() {
		col = 0;
		row = 0;
	}

	// Constructor con parametros
	public Position(int c, int r) {
		col = c;
		row = r;
	}

	// Setters
	// Funcion para actualizar la posicion
	public void update(Direction dir) {
		add(dir);
	}

	// Funcion para establecer la posicion
	private void add(Direction dir) {
		// Suma la columna de la dirección a la columna actual
		col = col + dir.getCol();
		// Suma la fila de la dirección a la fila actual
		row = row + dir.getRow();
	}

	// Getters
	// Funcion para obtener la futura direccion
	public Position nextPosition(Direction dir) {
		// Crea una nueva posición sumando la dirección a la posición actual
		return new Position(col + dir.getCol(), row + dir.getRow());
	}

	// Verifica si la posición está dentro de los límites de las columnas
	public boolean insideColsLimits(int limitX) {
		return this.col >= 0 && this.col < limitX;
	}

	// Verifica si la posición está dentro de los límites de las filas
	public boolean insideRowsLimits(int limitY) {
		return this.row >= 0 && this.row < limitY;
	}

	// Verifica si la posición está chocando con los límites de las columnas
	public boolean crashingIntoLimits(){
		return !insideColsLimits(Game.DIM_X);
	}

	// Verifica si la posición está dentro de los límites de las filas del juego
	public boolean insideGameRowsLimits(){
		return !insideRowsLimits(Game.DIM_Y);
	}

	// Funcion para ver si la posicion esta dentro de cualquier limite del tabler
	public boolean insideLimits() {
		return insideColsLimits(Game.DIM_X) && insideRowsLimits(Game.DIM_Y);
	}

	// Compara si esta posición es igual a otra posición
	public boolean isEqualTo(Position pos) {
		return this.col == pos.col && this.row == pos.row;
	}

	@Override
	public String toString() {
        return Messages.POSITION.formatted(String.valueOf(row), String.valueOf(col));
	}

	public Position clone(){
		return new Position(col, row);
	}
}
