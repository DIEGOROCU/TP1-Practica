package tp1.control.commands;

import tp1.logic.Interfaces.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public class ExitCommand extends NoParamsCommand{

	// Forman parte de atributos de estado
	private static final String NAME = Messages.COMMAND_EXIT_NAME;
	private static final String SHORTCUT = Messages.COMMAND_EXIT_SHORTCUT;
	private static final String DETAILS = Messages.COMMAND_EXIT_DETAILS;
	private static final String HELP = Messages.COMMAND_EXIT_HELP;

	// Constructor
	public ExitCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP); 
	}

	// Funcion para parsear el comando
	@Override
	public Command parse(String[] commandWords){
		Command com = null;
		if(matchCommandName(commandWords[0])) com = new ExitCommand();
		return com;
	}

	// Funcion para ejecutar el comando (sale del juego)
	@Override
	public void execute(GameModel game, GameView view){
		game.exit();
	}

}
