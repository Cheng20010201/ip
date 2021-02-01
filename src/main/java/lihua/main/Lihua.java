package lihua.main;

import lihua.commands.Command;
import lihua.commands.CommandResult;
import lihua.commands.ExitCommand;
import lihua.parser.Parser;
import lihua.storage.Storage;
import lihua.tasks.Tasks;
import lihua.ui.Ui;

// Design idea adapted from https://github.com/se-edu/addressbook-level2
public class Lihua {
    private final Tasks tasks;
    private final Storage storage;
    private final Ui ui;
    private final Parser parser;

    /**
     * Constructor for Lihua. Initializing necessary fields.
     */
    public Lihua() {
        ui = new Ui();
        storage = new Storage();
        tasks = storage.load();
        parser = new Parser();
    }

    public String getResponse(String userInput) {
        Command command = parser.parseUserInput(userInput);
        if (ExitCommand.isExit(command)) {
            exit();
        }
        CommandResult result = executeCommand(command);
        return result.getFeedBack();
    }
    /**
     * Executes the command argument. If successful then saves the current task list.
     * If there are unchecked exception then shows user the error message and throws an RuntimeException.
     * Throwing RuntimeException is because the user would not
     * be expected to handle the unchecked exception him/herself.
     *
     * @param command The command to be executed.
     * @return The CommandResult of the command's execution.
     */
    private CommandResult executeCommand(Command command) {
        try {
            command.setTaskList(tasks);
            CommandResult result = command.execute();
            storage.saveTasks(tasks);
            return result;
        } catch (Exception e) {
            ui.showFeedbackToUser(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void exit() {
        System.exit(0);
    }
}