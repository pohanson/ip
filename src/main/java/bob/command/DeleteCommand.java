package bob.command;

import bob.task.Task;

/**
 * Command to delete a task.
 */
public class DeleteCommand extends Command {
    private final int taskNumber;

    /**
     * Constructs DeleteCommand.
     *
     * @param taskNumber The task number to be deleted, 0-indexed.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Deletes the task at taskNumber (0-indexed)
     */
    @Override
    public void execute(bob.task.TaskList tasks, bob.ui.Ui ui, bob.storage.Storage storage) {
        if (tasks.validateTaskIndex(taskNumber)) {
            Task removedTask = tasks.remove(taskNumber);
            ui.printSection(String.format("I've removed this task:\n\t%s\nNow you've %d tasks in the list.",
                    removedTask, tasks.size()));
        } else {
            ui.printSection("Invalid task number: " + taskNumber);
        }
    }
}
