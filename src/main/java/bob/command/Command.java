package bob.command;

import bob.storage.Storage;
import bob.task.TaskList;
import bob.ui.Ui;

/**
 * Class that represents a command.
 * <p>
 * It should be inherited by other Command classes. The subclass should override
 * the abstract execute method.
 */
public abstract class Command {
    /**
     * Executes the command, when called by the Bob application.
     *
     * @param tasks   the TaskList to use.
     * @param ui      the user interface.
     * @param storage the storage.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
