package tp1.control.commands;

import tp1.exceptions.CommandExecuteException;
import tp1.logic.Interfaces.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public class ResetCommand extends Command{

	// Forman parte de atributos de estado
	 
    private static final String NAME = Messages.COMMAND_RESET_NAME;
    private static final String SHORTCUT = Messages.COMMAND_RESET_SHORTCUT;
    private static final String DETAILS = Messages.COMMAND_RESET_DETAILS;
    private static final String HELP = Messages.COMMAND_RESET_HELP;

	private int level;

	// Constructor
	public ResetCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP); 
		level = - 1;
	}

	// Funcion para parsear el comando
    @Override
    public Command parse(String[] commandWords) {
		Command com = null;
        if (matchCommandName(commandWords[0])) {
			// Comando reset por defecto
			ResetCommand newCommand =  new ResetCommand();
			// Comprobamos si se ha introducido un nivel
            if (commandWords.length > 1) {
                try {
					// Establecemos el nivel
                    newCommand.level = Integer.parseInt(commandWords[1]);
                } catch (NumberFormatException e) {}
				com = newCommand;
            } else {
                com = new ResetCommand();
            }
        }
        return com;
    }

	// Funcion para ejecutar el comando (resetea el juego al nivel indicado, o al nivel actual si no se indica ninguno, e indica un mensaje de error si el nivel no existe)
	@Override
	public void execute(GameModel game, GameView view) throws CommandExecuteException {
		if(level == -1){
			game.reset(game.getLevel());
			view.showGame();
		}
		else if(game.existsLevel(level)){
			game.resetToDefaultConfiguration();
			game.reset(level);
			view.showGame();
		}
		else {
			throw new CommandExecuteException(Messages.INVALID_LEVEL_NUMBER);
		}
	}

}