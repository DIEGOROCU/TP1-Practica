package tp1.control.commands;

import tp1.logic.Interfaces.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public class UpdateCommand extends NoParamsCommand{

	// Forman parte de atributos de estado
	private static final String NAME = Messages.COMMAND_UPDATE_NAME;
	private static final String SHORTCUT = Messages.COMMAND_UPDATE_SHORTCUT;
	private static final String DETAILS = Messages.COMMAND_UPDATE_DETAILS;
	private static final String HELP = Messages.COMMAND_UPDATE_HELP;

	// Constructor
	public UpdateCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP); 
	}

	// Funcion para parsear el comando
	public Command parse(String[] commandWords){
		Command com = null;
		if(matchCommandName(commandWords[0])) com = new UpdateCommand();
		return com;
	}

	// Funcion para saber si el comando es correcto (overridea porque el comando puede ser vacio)
	@Override
	protected boolean matchCommandName(String name) {
		return getShortcut().equalsIgnoreCase(name) || getName().equalsIgnoreCase(name) || name.equals(Messages.EMPTY);
	}

	// Funcion para ejecutar el comando (actualiza el juego)
	@Override
	public void execute(GameModel game, GameView view){
		game.update();
		view.showGame();
	}
}