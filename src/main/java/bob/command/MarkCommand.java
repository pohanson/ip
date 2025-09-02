package bob.command;

import bob.storage.Storage;
import bob.task.TaskList;
import bob.ui.Ui;

/**
 * Command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int taskNumber;

    /**
     * Constructs MarkCommand.
     *
     * @param taskNumber the task number to be marked as done (0-indexed)
     */
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Marks a task as done.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.validateTaskIndex(taskNumber)) {
            tasks.markDone(taskNumber);
            ui.printSection("I've marked this task as done:\n\t" + tasks.get(taskNumber));
        } else {
            ui.printSection("Invalid task number: " + taskNumber);
        }
    }
}
