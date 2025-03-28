package tp1.logic.lemmingRoles;

import tp1.logic.gameobjects.*;

public interface LemmingRole {

    // Funciones autodescriptivas que se implementan en las clases concretas
    public void start(Lemming lemming);
    public void play (Lemming lemming);
    public String getIcon(Lemming lemming);
    abstract public LemmingRole parse(String input);
    abstract public boolean roleMatch(String input);
    abstract public String helpText();
    abstract public String getSymbol();
    abstract public boolean interactWith(Wall wall, Lemming lemming);
    abstract public boolean interactWith(MetalWall metalWall, Lemming lemming);
    abstract public boolean interactWith(Lemming receiver, Lemming lemming);
    abstract public boolean interactWith(ExitDoor exit, Lemming lemming);
    abstract public boolean matchRoleName(String str);
    abstract public String getName();

}
