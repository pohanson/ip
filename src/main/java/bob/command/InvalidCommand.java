package bob.command;

import bob.storage.Storage;
import bob.task.TaskList;
import bob.ui.Ui;

/**
 * Command to handle invalid user input.
 */
public class InvalidCommand extends Command {
    private String input;

    /**
     * Constructs InvalidCommand
     * 
     * @param input the user input provided
     */
    public InvalidCommand(String input) {
        this.input = input;
    }

    /**
     * Prints the error message for invalid command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printSection("Invalid command: " + input);
        ui.printHelp();
    }
}
