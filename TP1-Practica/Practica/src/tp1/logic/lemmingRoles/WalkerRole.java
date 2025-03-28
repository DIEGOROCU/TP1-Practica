package tp1.logic.lemmingRoles;

import tp1.logic.*;
import tp1.logic.gameobjects.*;
import tp1.view.Messages;

public class WalkerRole extends AbstractRole implements LemmingRole {

    // Atributos
    private static final String NAME = Messages.WALKER_ROLE_NAME;
    private static final String SYMBOL = Messages.WALKER_ROLE_SYMBOL;
    private static final String RIGHT_ICON = Messages.LEMMING_RIGHT;
    private static final String LEFT_ICON = Messages.LEMMING_LEFT;
    private static final String HELP = Messages.WALKER_ROLE_HELP;
    private static final String DETAILS = Messages.WALKER_ROLE_DETAILS;

    // Constructor
    public WalkerRole() {
        super(NAME, DETAILS, HELP, RIGHT_ICON, SYMBOL);
    }

    // Metodos
    // Funcion para que el walking lemming se mueva
    @Override
	public void play(Lemming lemming) {
        //Si no interactua con nada, se mueve con normalidad
        if(!lemming.interactWithEverything()){ 
            lemming.move();
        } 
        // Si ha interactuado, pero sigue vivo rebota
        else if (lemming.isAlive()) { 
            lemming.inverseDirection();
        }
	}

    // Funcion para obtener el icono del lemming segun su direccion
    @Override
	public String getIcon(Lemming lemming) {
        if(lemming.getDirection() == Direction.RIGHT) {
            return RIGHT_ICON;
        } else {
            return LEFT_ICON;
        }
    }

    // Funcion para startear el walking lemming (no hace nada)
    @Override
    public void start(Lemming lemming) {
        //No hace nada
    }

    // Funcion que comprueba si el rol es el correcto
    @Override
    public LemmingRole parse(String input) {
        if (roleMatch(input)) return new WalkerRole();
        return null;
    }

    // Funcion para interactuar con una pared
    @Override
    public boolean interactWith(Wall wall, Lemming lemming) {
        boolean interaction = false;
        //Si el lemming andante (que tiene una pared debajo) choca con una pared o con los limites laterales del eje de abscisas, rebota
        if(!lemming.isInAir() && lemming.bounceIntoWall(wall)){
            interaction = true;
        }
        //Si el lemming andante choca con una pared con la suficiente energia cinetica, se muere
        else if((lemming.crashingIntoWall(wall) && lemming.tooKinectEnergy())){
            lemming.dies();
            lemming.addDeadLemmings();
            interaction = true;
        }
        return interaction;
    }
    
    // Funcion para interactuar con una pared de metal
    @Override
    public boolean interactWith(MetalWall wall, Lemming lemming) {
        boolean interaction = false;
        //Si el lemming andante (que tiene una pared debajo) choca con una pared o con los limites laterales del eje de abscisas, rebota
        if(!lemming.isInAir() && lemming.bounceIntoWall(wall)){
            interaction = true;
        }
        //Si el lemming andante choca con una pared con la suficiente energia cinetica, se muere
        else if((lemming.crashingIntoWall(wall) && lemming.tooKinectEnergy())){
            lemming.dies();
            lemming.addDeadLemmings();
            interaction = true;
        }
        return interaction;
    }
}


