package lihua.commands;

import java.time.LocalDate;

/**
 * Command class representing a command to list tasks.
 */
public class ListCommand extends Command {
    /** Optional argument indicating the date of tasks to be listed */
    private final LocalDate date;
    /** Command word for list command */
    public static final String COMMAND_WORD = "list";
    /** Command help information for list command */
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": List done all the tasks. "
            + "List done all the tasks on a specific date, if additional date argument is given\n"
            + "---- Example 1: " + COMMAND_WORD + "\n"
            + "---- Example 2: " + COMMAND_WORD + " [yyyy-mm-dd]";

    /**
     * Initializes a new ListCommand with a LocalDate.
     *
     * @param date The date on which the tasks should be listed.
     */
    public ListCommand(LocalDate date) {
        super();
        this.date = date;
    }

    /**
     * Initializes a default ListCommand, listing all existing tasks.
     */
    public ListCommand() {
        super();
        this.date = null;
    }

    /**
     * Executes the list command.
     * List all the tasks or all the tasks on a specific date.
     *
     * @return A CommandResult Object containing feedback to user.
     */
    @Override
    public CommandResult execute() {
        // Let lihua.tasks.Tasks check if it is null or not, then perform relevant operations.
        String message = tasks.listTasks(date);
        return new CommandResult(message);
    }
}
