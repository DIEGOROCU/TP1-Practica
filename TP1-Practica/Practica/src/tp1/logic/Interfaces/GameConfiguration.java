package tp1.logic.Interfaces;

import tp1.logic.GameObjectContainer;
public interface GameConfiguration {

    // game status
    public int getCycle();
    public int numLemmingInBoard();
    public int numLemmingsDead();
    public int numLemmingsExit();
    public int numLemmingsToWin();

    public GameObjectContainer getGameObjects();

    //public void save(String fileName) throws GameSaveException;

}