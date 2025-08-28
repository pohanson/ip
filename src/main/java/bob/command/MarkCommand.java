package bob.command;

public class MarkCommand extends Command {
    private int taskNumber;

    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(bob.task.TaskList tasks, bob.ui.Ui ui, bob.storage.Storage storage) {
        if (tasks.validateTaskIndex(taskNumber)) {
            tasks.markDone(taskNumber);
            ui.printSection("I've marked this task as done:\n\t" + tasks.get(taskNumber));
        } else {
            ui.printSection("Invalid task number: " + taskNumber);
        }
    }
}
