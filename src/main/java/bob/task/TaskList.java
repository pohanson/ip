package bob.task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.IntStream;

import bob.storage.Storage;

/**
 * TaskList manages a list of tasks and helps to perform action on them.
 */
public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>(100);
    private final Storage storage;

    /**
     * Constructs TaskList.
     */
    public TaskList(Storage storage) {
        this.storage = storage;
    }

    /**
     * Returns the list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the size of task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the task list is empty.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Adds a tasks to the list.
     *
     * @param t the task to add
     * @return true if the task was added successfully
     */
    public boolean add(Task t) {
        boolean result = tasks.add(t);
        if (result) {
            this.saveToStorage();
        }
        return result;
    }

    /**
     * Gets a task from the list.
     *
     * @param index the 0-based index of the task
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Marks a task as done.
     *
     * @param index the 0-based index of the task
     */
    public Task markDone(int index) {
        Task t = tasks.get(index).markDone();
        this.saveToStorage();
        return t;
    }

    /**
     * Unmarks a task as done.
     *
     * @param index the 0-based index of the task
     */
    public Task unmarkDone(int index) {
        Task t = tasks.get(index).unmarkDone();
        this.saveToStorage();
        return t;
    }

    /**
     * Removes a task from the list.
     *
     * @param index the 0-based index of the task
     */
    public Task remove(int index) {
        Task t = tasks.remove(index);
        this.saveToStorage();
        return t;
    }

    /**
     * Validates the task index.
     *
     * @param index the 0-based index of the task
     * @return true if the index is valid, else false.
     */
    public boolean validateTaskIndex(int index) {
        return (index >= 0 && index < tasks.size());

    }

    @Override
    public String toString() {
        return stringifyTasks();
    }

    /**
     * Converts the tasks in the list as a string for output.
     */
    public String stringifyTasks() {
        return stringifyTasks("");
    }

    /**
     * Filter and converts the tasks in the list as a string for output
     *
     * @param filterKeyword the keyword to search by.
     */
    public String stringifyTasks(String filterKeyword) {
        if (tasks.isEmpty()) {
            return "No tasks in list.";
        } else {
            return IntStream
                    .range(0, tasks.size())
                    .filter(i -> tasks.get(i).containsDescription(filterKeyword))
                    .mapToObj(i -> String.format("%d. %s", i + 1, tasks.get(i)))
                    .reduce("", (acc, cur) -> acc + "\n\t" + cur);
        }
    }

    /**
     * Converts the tasks in the list as a string for storage.
     */
    public String toStorageString() {
        if (tasks.isEmpty()) {
            return "";
        } else {
            return tasks.stream()
                    .map(task -> (task.isDone ? "1" : "0") + " " + task.toInputString())
                    .reduce("", (acc, x) -> acc + x + "\n");
        }
    }

    /**
     * Saves the current task list to storage.
     */
    public void saveToStorage() {
        try {
            storage.saveTaskList(this);
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}
