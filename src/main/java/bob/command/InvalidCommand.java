package bob.command;

import bob.storage.Storage;
import bob.task.TaskList;
import bob.ui.Ui;

public class InvalidCommand extends Command {
    private String input;

    public InvalidCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printSection("Invalid command: " + input);
        ui.printHelp();
    }
}
