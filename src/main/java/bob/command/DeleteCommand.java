package bob.command;

import java.util.ArrayList;
import java.util.Arrays;

import bob.task.Task;
import bob.task.TaskList;
import bob.ui.Ui;

/**
 * Command to delete a task.
 */
public class DeleteCommand extends Command {
    private final Integer[] taskNumbers;

    /**
     * Constructs DeleteCommand.
     *
     * @param taskNumbers The task numbers to be deleted, 1-indexed.
     */
    public DeleteCommand(Integer[] taskNumbers) {
        // Sort in descending order so that the deletion of tasks does not affect the indices of tasks yet to be deleted.
        this.taskNumbers = Arrays.stream(taskNumbers).sorted((a, b) -> b - a).toArray(Integer[]::new);
    }

    /**
     * Deletes the tasks at taskNumbers (1-indexed)
     */
    @Override
    public void execute(bob.task.TaskList tasks, bob.ui.Ui ui, bob.storage.Storage storage) {
        if (super.isValidTasks(tasks, taskNumbers)) {
            this.deleteAllTasks(tasks, ui);
        } else {
            ui.printSection("Invalid task number(s) provided: " + Arrays.toString(taskNumbers));
        }
    }

    private void deleteAllTasks(TaskList tasks, Ui ui) {
        ArrayList<Task> tasksDeleted = new ArrayList<>();
        for (int taskNumber : taskNumbers) {
            tasksDeleted.add(tasks.remove(taskNumber - 1));
        }
        String prompt = taskNumbers.length == 1 ? "I've removed this task:\n\t" : "I've removed these tasks:\n\t";
        ui.printSection(prompt + TaskList.stringifyTasks(tasksDeleted)
                + String.format("\nNow you've %d tasks in the list.", tasks.size()));
    }
}
