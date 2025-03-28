package tp1.control.commands;

import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.GameSaveException;
import tp1.logic.Interfaces.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public class SaveCommand extends Command{

    private static final String NAME = Messages.COMMAND_SAVE_NAME;
    private static final String SHORTCUT = Messages.COMMAND_SAVE_SHORTCUT;
    private static final String DETAILS = Messages.COMMAND_SAVE_DETAILS;
    private static final String HELP = Messages.COMMAND_SAVE_HELP;

    // Atributos privados y particulares de este comando
    private String fileName;

    public SaveCommand() {
        super(NAME, SHORTCUT, DETAILS, HELP);
    }

    // Ejecuta el comando save
    @Override
    public void execute(GameModel game, GameView view) throws CommandExecuteException {
        try {
            // Intenta guardar el juego en el archivo
            game.save(fileName);
            view.showMessage(Messages.FILE_SAVED.formatted(fileName));
        } catch (GameSaveException e) {
            game.update();
            throw new CommandExecuteException(Messages.INVALID_FILE_CONFIGURATION.formatted(fileName) ,e);
        }
        //view.showGame();
    }


    @Override
    public Command parse(String[] commandWords) {
        if (matchCommandName(commandWords[0])) {
            SaveCommand newCommand = new SaveCommand();
            newCommand.fileName = commandWords[1];
            return newCommand;
        }
        return null;
    }
}
