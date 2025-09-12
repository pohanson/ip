package bob.command;

import java.util.ArrayList;
import java.util.Arrays;

import bob.storage.Storage;
import bob.task.Task;
import bob.task.TaskList;
import bob.ui.Ui;

/**
 * Command to mark a task as undone.
 */
public class UnmarkCommand extends Command {
    private final Integer[] taskNumbers;

    /**
     * Constructs UnmarkCommand.
     *
     * @param taskNumbers the list of task numbers to be marked as undone (1-indexed).
     */
    public UnmarkCommand(Integer[] taskNumbers) {
        this.taskNumbers = taskNumbers;
    }

    /**
     * Marks a task as undone.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (super.isValidTasks(tasks, taskNumbers)) {
            this.markAllTaskUndone(tasks, ui);
        } else {
            ui.printSection("Invalid task number(s) provided: " + Arrays.toString(taskNumbers));
        }
    }

    private void markAllTaskUndone(TaskList tasks, Ui ui) {
        ArrayList<Task> tasksUnmarked = new ArrayList<>();
        for (int taskNumber : taskNumbers) {
            tasksUnmarked.add(tasks.unmarkDone(taskNumber - 1));
        }
        String prompt = taskNumbers.length == 1 ? "I've marked this task as undone:\n\t"
                                        : "I've marked these tasks as undone:\n\t";
        ui.printSection(prompt + TaskList.stringifyTasks(tasksUnmarked));
    }
}
