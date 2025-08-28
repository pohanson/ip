package bob.command;

public class ExitCommand extends Command {
    public ExitCommand() {
    }

    @Override
    public void execute(bob.task.TaskList tasks, bob.ui.Ui ui, bob.storage.Storage storage) {
        ui.printSection("Bye. Hope to see you again soon!");
    }
}
