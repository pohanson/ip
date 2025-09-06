package bob.command;

import bob.exception.InvalidInputException;
import bob.storage.Storage;
import bob.task.Task;
import bob.task.TaskList;
import bob.ui.Ui;

/**
 * Command to add a task.
 */
public class AddCommand extends Command {
    private final String input;

    /**
     * Constructs AddCommand.
     *
     * @param input the user input
     */
    public AddCommand(String input) {
        this.input = input;
    }

    /**
     * Parses the input and adds the task to tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = Task.createFromString(input);
            tasks.add(task);
            ui.printSection(String.format("I've added this task:\n\t %s\nNow you've %d tasks in the list.", task,
                    tasks.size()));
        } catch (InvalidInputException e) {
            ui.printSection(e.getMessage());
        }
    }

}
