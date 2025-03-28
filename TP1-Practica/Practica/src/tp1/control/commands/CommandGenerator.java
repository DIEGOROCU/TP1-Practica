package tp1.control.commands;

import java.util.Arrays;
import java.util.List;
import tp1.exceptions.CommandParseException;
import tp1.view.Messages;
public class CommandGenerator {

	// Constante que contiene los comandos disponibles
	private static final List<Command> availableCommands = Arrays.asList(
		new SetRoleCommand(),
		new UpdateCommand(),
        new ResetCommand(),
		new LoadCommand(),
		new SaveCommand(),
        new HelpCommand(),
        new ExitCommand()
	);

	// Funcion para parsear el comando general, recorre los comandos disponibles y llama a su parse
	public static Command parse(String[] commandWords) throws CommandParseException {		
		// Recorremos los comandos disponibles en busca del comando introducido
		for (Command c: availableCommands) {
			Command command = c.parse(commandWords);
			if (command != null) {
				return command;
			}
		}
		// Si no se ha encontrado el comando, se lanza una excepcion
		throw new CommandParseException(Messages.UNKNOWN_COMMAND.formatted(commandWords[0]));
	}
		
	// Funcion para mostrar la ayuda de los comandos
	public static String commandHelp() {
		StringBuilder commands = new StringBuilder();
		for (Command c: availableCommands) {
			commands.append(c.helpText());
		}
		return commands.toString();
	}

}