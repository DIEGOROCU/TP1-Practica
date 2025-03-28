package tp1.logic.Interfaces;

import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.GameLoadException;
import tp1.exceptions.GameSaveException;
import tp1.exceptions.OffBoardException;
import tp1.logic.Position;
import tp1.logic.lemmingRoles.LemmingRole;

public interface GameModel {

    // Funciones autodescriptivas definidas en Game
	abstract public boolean isFinished();
    abstract public int getLevel();
    abstract public void update();
    abstract public void reset(int level) throws CommandExecuteException;
    abstract public void exit();
    abstract public boolean setRole(LemmingRole role, Position pos, String roleName) throws OffBoardException;
	abstract public boolean existsLevel(int l);
    abstract public void increaseCycle();

    // Funcion load que carga un archivo
    abstract public void load(String fileName) throws GameLoadException;

    // Funcion save que guarda un archivo
    abstract public void save(String fileName) throws GameSaveException;

    // Funcion fija la configuracion del juego
    abstract public void setConfiguration();

    // Funcion que resetea la configuracion del juego
    abstract public void resetToDefaultConfiguration();
}
