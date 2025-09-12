package bob.command;

import java.util.ArrayList;
import java.util.Arrays;

import bob.storage.Storage;
import bob.task.Task;
import bob.task.TaskList;
import bob.ui.Ui;

/**
 * Command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final Integer[] taskNumbers;

    /**
     * Constructs MarkCommand.
     *
     * @param taskNumbers the list of task numbers to be marked as done
     *                    (1-indexed).
     */
    public MarkCommand(Integer[] taskNumbers) {
        this.taskNumbers = taskNumbers;
    }

    /**
     * Marks tasks as done.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (super.isValidTasks(tasks, taskNumbers)) {
            this.markAllTaskDone(tasks, ui);
        } else {
            ui.printSection("Invalid task number(s) provided: " + Arrays.toString(taskNumbers));
        }
    }

    private void markAllTaskDone(TaskList tasks, Ui ui) {
        ArrayList<Task> tasksMarked = new ArrayList<>();
        for (int taskNumber : taskNumbers) {
            tasksMarked.add(tasks.markDone(taskNumber - 1));
        }
        String prompt = taskNumbers.length == 1 ? "I've marked this task as done:\n\t"
                : "I've marked these tasks as done:\n\t";
        ui.printSection(prompt + TaskList.stringifyTasks(tasksMarked));
    }
}
