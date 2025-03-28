package tp1.logic.Interfaces;
import tp1.logic.*;
import tp1.logic.gameobjects.GameItem;

public interface GameWorld {

    // Funciones autodescriptivas definidas en Game
    abstract boolean isInAir(Position pos);
    abstract void addExitLemmings();
    abstract boolean leavingTheBoard(Position pos);
    abstract void addDeadLemmings();
    abstract int getLevel();
    abstract boolean receiveInteractionsFrom(GameItem obj);
    abstract boolean positionInLimits(Position pos);
}
