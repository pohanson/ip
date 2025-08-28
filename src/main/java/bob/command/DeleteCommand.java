package bob.command;

import bob.task.Task;

public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(bob.task.TaskList tasks, bob.ui.Ui ui, bob.storage.Storage storage) {
        if (tasks.validateTaskIndex(taskNumber)) {
            Task removedTask = tasks.remove(taskNumber);
            ui.printSection(String.format("I've removed this task:\n\t%s\nNow you've %d tasks in the list.",
                    removedTask,
                    tasks.size()));
        } else {
            ui.printSection("Invalid task number: " + taskNumber);
        }
    }
}
