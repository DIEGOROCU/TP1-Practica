package tp1.logic.Interfaces;

import tp1.logic.gameobjects.GameItem;

public interface GameStatus {

    // Funciones autodescriptivas definidas en Game
    abstract int getCycle();
    abstract int getLevel();
    //abstract int getNumLemmings();
    abstract int numLemmingsInBoard();
    abstract int numLemmingsDead();
    abstract int numLemmingsExit();
    abstract int numLemmingsToWin();
    abstract int numLemmingsLeftToWin();
    abstract String positionToString(int col, int row);
    abstract boolean receiveInteractionsFrom(GameItem obj);

}
