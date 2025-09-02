package bob.command;

import bob.storage.Storage;
import bob.task.TaskList;
import bob.ui.Ui;

/**
 * Command to handle invalid user input.
 */
public class InvalidCommand extends Command {
    private String header;
    private String details;

    /**
     * Constructs InvalidCommand
     *
     * @param header the user input provided
     */
    public InvalidCommand(String header) {
        this.header = header;
        this.details = "";
    }

    /**
     * Constructs InvalidCommand
     *
     * @param header the summary header
     * @param details details about the error
     */
    public InvalidCommand(String header, String details) {
        this.header = header;
        this.details = details;
    }

    /**
     * Prints the error message for invalid command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError(String.format("Invalid command: %s\n%s", header, details));
        ui.printHelp();
    }
}
