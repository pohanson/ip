package bob.command;

import bob.storage.Storage;
import bob.task.Task;
import bob.task.TaskList;
import bob.ui.Ui;

/**
 * Command to mark a task as undone.
 */
public class UnmarkCommand extends Command {
    private final int taskNumber;

    /**
     * Constructs UnmarkCommand.
     *
     * @param taskNumber the task number to be marked as done (0-indexed)
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Marks a task as undone.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.validateTaskIndex(taskNumber)) {
            Task task = tasks.unmarkDone(taskNumber);
            ui.printSection("I've marked this task as undone:\n\t" + task);
        } else {
            ui.printSection("Invalid task number: " + taskNumber);
        }
    }
}
