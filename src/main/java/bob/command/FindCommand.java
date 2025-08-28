package bob.command;

import bob.storage.Storage;
import bob.task.TaskList;
import bob.ui.Ui;

public class FindCommand extends Command {
    private String input;

    public FindCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printSection("Here are the matching tasks in your list: " + tasks.stringifyTasks(this.input));
    }
}
