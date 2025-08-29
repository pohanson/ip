package bob.command;

import bob.storage.Storage;
import bob.task.TaskList;
import bob.ui.Ui;

public class InvalidCommand extends Command {
    private String header;
    private String details;

    public InvalidCommand(String header) {
        this.header = header;
        this.details = "";
    }

    public InvalidCommand(String header, String details) {
        this.header = header;
        this.details = details;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError(String.format("Invalid command: %s\n%s", header, details));
        ui.printHelp();
    }
}
