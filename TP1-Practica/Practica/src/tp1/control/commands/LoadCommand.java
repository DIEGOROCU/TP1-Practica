package tp1.control.commands;

import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.GameLoadException;
import tp1.logic.Interfaces.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public class LoadCommand extends Command{

    private static final String NAME = Messages.COMMAND_LOAD_NAME;
    private static final String SHORTCUT = Messages.COMMAND_LOAD_SHORTCUT;
    private static final String DETAILS = Messages.COMMAND_LOAD_DETAILS;
    private static final String HELP = Messages.COMMAND_LOAD_HELP;

    // Atributos privados y particulares de este comando
    private String fileName;

    public LoadCommand() {
        super(NAME, SHORTCUT, DETAILS, HELP);
    }

    // Funcion que carga un archivo
    @Override
    public void execute(GameModel game, GameView view) throws CommandExecuteException {
        // Cargamos el archivo
        try {
            game.load(fileName);
        } // Si no se puede cargar el archivo, lanzamos una excepcion
        catch (GameLoadException e) {
            throw new CommandExecuteException(Messages.INVALID_FILE_CONFIGURATION.formatted(fileName) ,e);
        }
        view.showGame();
    }

    // Funcion para leer el comando y el nombre del archivo en el que esta 
    // almacenado el juego
    @Override
    public Command parse(String[] commandWords) {
        if (matchCommandName(commandWords[0])) {
            LoadCommand newCommand = new LoadCommand();
            newCommand.fileName = commandWords[1];
            return newCommand;
        }
        return null;
    }
}
