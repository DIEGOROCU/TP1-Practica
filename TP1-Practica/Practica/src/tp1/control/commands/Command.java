package tp1.control.commands;

import tp1.exceptions.*;
import tp1.logic.Interfaces.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public abstract class Command {

	// Forman parte de atributos de estado
	private final String name;
	private final String shorcut;
	private final String details;
	private final String help;
	
	public Command(String name, String shorcut, String details, String help) {
		this.name = name;
		this.shorcut = shorcut;
		this.details = details;
		this.help = help;
	}

	protected String getName() { return name; }
	protected String getShortcut() { return shorcut; }
	protected String getDetails() { return details; }
	protected String getHelp() { return help; }

	// Metodos abstractos
	public abstract void execute(GameModel game, GameView view) throws CommandExecuteException;
		  
	public abstract Command parse(String[] commandWords) throws CommandParseException;
	// Usa los metodos de la clase String para saber que comando se ha introducido

	protected boolean matchCommandName(String name) {
		return getShortcut().equalsIgnoreCase(name) || getName().equalsIgnoreCase(name);
	}

	// Funcion general para mostrar la ayuda de todos los comandos
	public String helpText(){
		StringBuilder help = new StringBuilder();
		help.append(Messages.COMMAND_HELP_TEXT.formatted(getDetails(), getHelp()));
		return Messages.LINE_TAB.formatted(help.toString());	
	}
}
