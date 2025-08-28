package bob.command;

/**
 * Command to exit the application.
 */
public class ExitCommand extends Command {
    /** Constructs ExitCommand. */
    public ExitCommand() {
    }

    /**
     * Prints exit message.
     */
    @Override
    public void execute(bob.task.TaskList tasks, bob.ui.Ui ui, bob.storage.Storage storage) {
        ui.printSection("Bye. Hope to see you again soon!");
    }
}
