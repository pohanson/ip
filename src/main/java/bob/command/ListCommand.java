package bob.command;

public class ListCommand extends Command {
    public ListCommand() {
    }

    @Override
    public void execute(bob.task.TaskList tasks, bob.ui.Ui ui, bob.storage.Storage storage) {
        ui.printSection(tasks.stringifyTasks());
    }
}
