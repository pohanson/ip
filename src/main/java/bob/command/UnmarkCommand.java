package bob.command;

import bob.storage.Storage;
import bob.task.Task;
import bob.task.TaskList;
import bob.ui.Ui;

public class UnmarkCommand extends Command {
    private int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

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
