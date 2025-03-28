package tp1.logic;

import java.util.ArrayList;
import tp1.logic.gameobjects.*;
import tp1.logic.lemmingRoles.LemmingRole;
import tp1.view.Messages;

public class GameObjectContainer {
	
	// Atributos
	private ArrayList<GameObject> gameObjects;

	// Constructores
	// Constructor por defecto
	public GameObjectContainer() {
		this.gameObjects = new ArrayList<GameObject>();
	}

	//Setters
	// Funcion para actualizar el estado de los lemmings
	public void update() {
		// Actualiza cada objeto en la lista
		gameObjects.forEach(n -> n.update());
	}

	// Getters
	// Metodo para obtener el objeto dado un indice
	public GameObject get(int index) {
		// Devuelve el objeto en el índice especificado
		return gameObjects.get(index);
	}
		
	// Metodos para añadir objetos del juego
		// Funcion para añadir un objeto
		public void add(GameObject gameObject) {
			// Añade un objeto a la lista
			gameObjects.add(gameObject);
		}

		// Elimina los objetos que no están vivos
		public void removeDeadObjects(){
			// Elimina los objetos muertos de la lista
			gameObjects.removeIf(n -> !n.isAlive());
		}

		// Verifica si hay una pared en la posición dada
		public boolean wallAtPosition(Position pos){
			// Recorre la lista de objetos para verificar si hay una pared en la posición
			for(int i = 0; i < gameObjects.size(); i++){
				if(gameObjects.get(i).isSolid() && gameObjects.get(i).isInPosition(pos)){
					return true; // Devuelve verdadero si hay una pared en la posición
				}
			}
			return false; // Devuelve falso si no hay una pared en la posición
		}

		/*public boolean exitAt(Position pos){
			for(int i = 0; i < gameObjects.size(); i++){
				if(gameObjects.get(i).isExit() && gameObjects.get(i).isInPosition(pos)){
					return true;
				}
			}
			return false;
		}*/

		// Convierte la posición a una cadena de texto
		public String positionToString(int col, int row){
			String str = Messages.EMPTY;
			Position pos = new Position(col, row);
			// Recorre la lista de objetos para añadir su representación en cadena si están en la posición
			for(int i = 0; i < gameObjects.size(); i++){
				if(gameObjects.get(i).isInPosition(pos)){
					str += gameObjects.get(i).toString();
				}
			}
			// Devuelve la cadena resultante
			return str.toString();
		}

		// Elimina un objeto de la lista
		public void remove(GameObject obj){
			// Elimina el objeto especificado de la lista
			gameObjects.remove(obj);
		}
		
		// Devuelve el índice del objeto en la posición dada
		/*public int objectAt(Position pos) {
			int index = -1;
			// Recorre la lista de objetos para encontrar el índice del objeto en la posición
			for(int i = 0; i < gameObjects.size() && index == -1; i++) {
				if(gameObjects.get(i).isInPosition(pos)) index = i;
			}
			// Devuelve el índice del objeto o -1 si no se encuentra
			return index;
		}*/
		
		// Establece el rol de un lemming en la posición dada
		public boolean setRole(LemmingRole role, Position pos, String roleName) {
			for (int i = 0; i < gameObjects.size(); i++) {
				if (gameObjects.get(i).isInPosition(pos) && gameObjects.get(i).setRole(role, roleName)) {
					return true; // Devuelve verdadero si se establece el rol
				}
			}
			return false;
		}

		// Recibe interacciones de un objeto del juego
		public boolean receiveInteractionsFrom(GameItem obj){
			boolean interaction = false;
			// Recorre la lista de objetos para recibir interacciones del objeto del juego
			for(GameObject gameObject : gameObjects){
				if(gameObject.receiveInteraction(obj)){
					interaction = true; // Marca que hubo una interacción
				}
			}
			// Devuelve verdadero si hubo alguna interacción
			return interaction;
		}

		// Funcion que clona el contenedor de objetos
		public GameObjectContainer clone(){
			// Crea un nuevo contenedor de objetos
			GameObjectContainer newContainer = new GameObjectContainer();
			// Recorre la lista de objetos para clonarlos
			for (GameObject gameObject : gameObjects) {
				newContainer.add(gameObject.clone());
			}
			// Devuelve el nuevo contenedor de objetos
			return newContainer;
		}

		// Devuelve un string con la representación de los objetos del juego
		public String toString() {
			// Crea una cadena vacía
			String str = "";
			// Recorre la lista de objetos para añadir su representación en cadena
			for (GameObject gameObject : gameObjects) {
				str += gameObject.toSaveString() + "\n";
			}
			// Devuelve la cadena resultante
			return str;
		}
}
