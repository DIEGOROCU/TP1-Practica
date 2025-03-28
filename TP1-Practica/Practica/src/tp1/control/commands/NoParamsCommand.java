package tp1.control.commands;

import tp1.exceptions.*;
import tp1.view.Messages;

public abstract class NoParamsCommand extends Command {

	// Constructor abstracto para los comandos sin parametros
	public NoParamsCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help);
	}
	
	// Si el comando no tiene parametros se devuelve el comando (null)
	public Command parse(String[] commandWords) throws CommandParseException {
		if (commandWords.length < 1 || !matchCommandName(commandWords[0]))
			return null;
		
		// Si el numero de palabras es 1, se devuelve el comando
		if (commandWords.length == 1 && matchCommandName(commandWords[0]))
			return this;
		
		// Si no, se lanza una excepcion
		throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
 }

}
