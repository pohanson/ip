package bob.command;

import bob.storage.Storage;
import bob.task.TaskList;
import bob.ui.Ui;

/**
 * Command to find a task by description
 */
public class FindCommand extends Command {
    /* User input string to search for */
    private final String input;

    /**
     * Constructs FindCommand.
     *
     * @param input the string to search.
     */
    public FindCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printSection("Here are the matching tasks in your list: " + TaskList
                .stringifyTasks(TaskList.filterTasksByKeyword(tasks.getTasks(), input)));
    }
}
