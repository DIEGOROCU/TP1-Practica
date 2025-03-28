package tp1.logic.lemmingRoles;

import tp1.logic.gameobjects.*;
import tp1.view.Messages;

public class DownCaverRole extends AbstractRole implements LemmingRole{

    // Atributos
    private static final String NAME = Messages.DOWN_CAVER_ROLE_NAME;
    private static final String SYMBOL = Messages.DOWN_CAVER_ROLE_SYMBOL;
    private static final String ICON = Messages.LEMMING_DOWN_CAVER;
    private static final String HELP = Messages.DOWN_CAVER_ROLE_HELP;
    private static final String DETAILS = Messages.DOWN_CAVER_ROLE_DETAILS;

    private boolean hasCaved;

    // Constructor
    public DownCaverRole(){
        super(NAME, DETAILS, HELP, ICON, SYMBOL);
        hasCaved = false;
    }

    // Metodos
    // Funcion start de DownCaver (no hace nada)
    @Override
    public void start(Lemming lemming){
    }

    // Funcion play de DownCaver
    @Override
    public void play (Lemming lemming){
        // Interactua con todo lo que haya en su camino
        lemming.interactWithEverything();
        // Si ha cavado, el lemming cae y se le pone la fuerza de caida a 0
        if(hasCaved){ 
            lemming.falls();
            lemming.featherFall();
            hasCaved = false;
        }
        else{
            // Si el lemming no ha cavado, se desactiva el rol y se mueve con normalidad manteniendo la direccion que tenia
            lemming.disableRole();
            lemming.featherFall();
            lemming.move();
        }
    }

    // Funcion que interactua con una pared
    @Override
    public boolean interactWith(Wall wall, Lemming lemming){
        boolean interaction = false;
        // Si el lemming choca con una pared, la destruye y cava
        if(lemming.crashingIntoWall(wall)){
            wall.dies();
            hasCaved = true;
            interaction = true;
        }
        // Devuelve si ha habido interaccion
        return interaction;
    }

    @Override
    public boolean interactWith(MetalWall metalWall, Lemming lemming){
        boolean interaction = false;
        // Si el lemming choca con una pared de metal, no cava y se desactiva el rol
        if(lemming.crashingIntoWall(metalWall)){
            hasCaved = false;
            interaction = true;
        }
        // Devuelve si ha habido interaccion
        return interaction;
    }

    // Funcion para comprobar si el rol es el correcto
    @Override
    public LemmingRole parse(String input){
        if (matchRoleName(input)) return new DownCaverRole();
        return null;    
    }
}
