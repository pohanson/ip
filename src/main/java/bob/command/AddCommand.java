package bob.command;

import bob.storage.Storage;
import bob.task.Task;
import bob.task.TaskList;
import bob.ui.Ui;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task t) {
        this.task = t;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.printSection(
                String.format("I've added this task:\n\t %s\nNow you've %d tasks in the list.", task,
                        tasks.size()));
    }

}
