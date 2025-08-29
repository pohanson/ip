package bob.command;

import bob.storage.Storage;
import bob.task.TaskList;
import bob.ui.Ui;

/**
 * Command to list all tasks.
 */
public class ListCommand extends Command {
    /** Constructs ListCommand. */
    public ListCommand() {
    }

    /**
     * Prints out all tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printSection(tasks.stringifyTasks());
    }
}
