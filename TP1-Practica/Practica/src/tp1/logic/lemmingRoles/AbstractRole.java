package tp1.logic.lemmingRoles;

import tp1.logic.gameobjects.*;
import tp1.view.Messages;

public class AbstractRole {

    // Atributos
    private final String name;
    private final String symbol;
    private final String icon; 
	private final String help;
	private final String details;

    // Constructor
	public AbstractRole(String name, String details, String help, String icon, String symbol) {
		this.name = name;
        this.symbol = symbol;
        this.icon = icon;
		this.help = help;
		this.details = details;
	}

    // Metodos
    // Funcion para obtener el icono del lemming
    public String getIcon(Lemming lemming){
        return icon;
    }

    // Funcion para obtener el simbolo del rol
    public String getSymbol(){
        return symbol;
    }

    // Funcion para obtener la ayuda del rol
    public String getHelp(){
        return help;
    }

    // Funcion para obtener los detalles del rol
    public String getDetails(){
        return details;
    }

    public String getName(){
        return name;
    }

    // Funcion para 
    public boolean matchRoleName(String str){
        return str.equalsIgnoreCase(name) || str.equalsIgnoreCase(symbol);
    }

    // Funcion para obtener el texto de ayuda del rol
    public String helpText(){
        StringBuilder help = new StringBuilder();
		help.append(Messages.COMMAND_HELP_TEXT.formatted(getDetails(), getHelp()));
		return Messages.LINE_2TABS.formatted(help.toString());
    }

    // Funciones para interactuar con los objetos del juego
    public boolean interactWith(Wall wall, Lemming lemming){
        return false; 
    }
    public boolean interactWith(Lemming receiver, Lemming lemming){
        return false; 
    }
    public boolean interactWith(ExitDoor exit, Lemming lemming){
        return false; 
    }
    public boolean interactWith(MetalWall metalWall, Lemming lemming){
        return false; 
    }

    // Funcion para comprobar si el rol es el correcto
    public boolean roleMatch(String input){
        return input.equalsIgnoreCase(name) || input.equalsIgnoreCase(symbol);
    }
}
