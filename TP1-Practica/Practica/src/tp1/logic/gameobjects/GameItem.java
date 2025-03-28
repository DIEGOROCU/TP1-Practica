package tp1.logic.gameobjects;
import tp1.logic.Position;

public interface GameItem {

	// Funciones autodescriptivas definidas en sus clases correspondientes
	public boolean receiveInteraction(GameItem other);

	abstract public boolean interactWith(Lemming lemming);
	abstract public boolean interactWith(Wall wall);
	abstract public boolean interactWith(ExitDoor door);
	abstract public boolean interactWith(MetalWall wall);

	abstract public boolean isSolid();
	abstract public boolean isAlive();
	abstract public boolean isInPosition(Position pos);
}