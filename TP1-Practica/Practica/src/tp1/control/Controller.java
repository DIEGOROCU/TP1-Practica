package tp1.control;

import java.awt.event.FocusEvent;
import tp1.control.commands.Command;
import tp1.control.commands.CommandGenerator;
import tp1.exceptions.CommandException;
import tp1.logic.Game;
import tp1.view.GameView;
import tp1.view.Messages;

/**
 *  Accepts user input and coordinates the game execution logic
 */
public class Controller {

	private Game game;
	private GameView view;

	public Controller(Game game, GameView view) {
		this.game = game;
		this.view = view;
	}


	/**
	 * Runs the game logic, coordinate Model(game) and View(view)
	 */
	public void run() {
		String[] words = null;
		view.showWelcome();
		view.showGame();
		// Mientras el juego no haya terminado
		while (!game.isFinished()) {
			// Leemos el comando
			words = view.getPrompt();
			try {
				Command command = CommandGenerator.parse(words);
				// Si el comando no es nulo lo ejecutamos y mostramos el juego
				if (command != null){
					command.execute(game, view);
				}
				// Si no, mostramos un mensaje de error
				else view.showError(Messages.UNKNOWN_COMMAND.formatted(words[0]));
			}   catch (CommandException e) {
					if(e.getCause() != null){
						view.showErrorWithoutNewLine(e.getMessage());
						view.showError(e.getCause().getMessage());
					}
					else view.showError(e.getMessage());
 			}
		}
		// Mostramos el mensaje de fin de juego
		view.showEndMessage();
	}
}
