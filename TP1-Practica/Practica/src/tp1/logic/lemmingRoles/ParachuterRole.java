package tp1.logic.lemmingRoles;

import tp1.logic.gameobjects.*;
import tp1.view.Messages;

public class ParachuterRole extends AbstractRole implements LemmingRole {

    // Atributos
    private static final String NAME = Messages.PARACHUTER_ROLE_NAME;
    private static final String SYMBOL = Messages.PARACHUTER_ROLE_SYMBOL;
    private static final String ICON = Messages.LEMMING_PARACHUTE;
    private static final String HELP = Messages.PARACHUTER_ROLE_HELP;
    private static final String DETAILS = Messages.PARACHUTER_ROLE_DETAILS;
    
    // Constructor
    public ParachuterRole() {
        super(NAME, DETAILS, HELP, ICON, SYMBOL);
    }
    
    // Metodos
    // Funcion start de parachuter (no hace nada)
    @Override
    public void start(Lemming lemming) {
    	// lemming.featherFall();
    }

    // Funcion play de parachuter
    @Override
    public void play(Lemming lemming) {
    	// Si no interactua con nada, cae
        if(!lemming.interactWithEverything() ){ 
            lemming.falls();
        }
        // Cuando interactua, en particular con una pared o una pared de metal
        // se pone la fuerza de caida a 0
        else {
        	lemming.featherFall();
            // Si choca con una pared, se desactiva el rol y se updatea el lemming con el nuevo rol
            lemming.update();
        }
    }

    // Funcion para comprobar si el rol es el correcto
    @Override 
    public LemmingRole parse(String input) {
        if (roleMatch(input)) return new ParachuterRole();
        return null;
    }


    // Funcion para interactuar con una pared
    @Override
    public boolean interactWith(Wall wall, Lemming lemming) {
        boolean interaction = false; 
        // Si el lemming choca con una pared, se desactiva el rol
        if(lemming.crashingIntoWall(wall)) {
            lemming.featherFall();
            lemming.disableRole();
            interaction = true;
        }
        // Devuelve si ha habido interaccion
        return interaction;
    }
    
    // Funcion para interactuar con una pared de metal
    @Override
    public boolean interactWith(MetalWall wall, Lemming lemming) {
        boolean interaction = false; 
        // Si el lemming choca con una pared de metal, se desactiva el rol
        if(lemming.crashingIntoWall(wall)) {
            lemming.featherFall();
            lemming.disableRole();
            interaction = true;
        }
        // Devuelve si ha habido interaccion
        return interaction;
    }
}
