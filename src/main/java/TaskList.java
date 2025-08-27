import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>(100);
    private Storage storage;

    public TaskList(Storage storage) {
        this.storage = storage;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public boolean add(Task t) {
        boolean result = tasks.add(t);
        if (result) {
            this.saveToStorage();
        }
        return result;
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public Task markDone(int index) {
        Task t = tasks.get(index).markDone();
        this.saveToStorage();
        return t;
    }

    public Task unmarkDone(int index) {
        Task t = tasks.get(index).unmarkDone();
        this.saveToStorage();
        return t;
    }

    public Task remove(int index) {
        Task t = tasks.remove(index);
        this.saveToStorage();
        return t;
    }

    public boolean validateTaskIndex(int index) {
        return (index >= 0 && index < tasks.size());

    }

    @Override
    public String toString() {
        return stringifyTasks();
    }

    public String stringifyTasks() {
        if (tasks.isEmpty()) {
            return "No tasks in list.";
        } else
            return IntStream.range(0, tasks.size())
                    .mapToObj(i -> String.format("%d. %s", i + 1, tasks.get(i)))
                    .reduce("", (acc, cur) -> acc + "\n\t" + cur);
    }

    public String toStorageString() {
        if (tasks.isEmpty()) {
            return "";
        } else {
            return tasks.stream()
                    .map(task -> (task.isDone ? "1" : "0") + " " + task.toInputString())
                    .reduce("", (acc, x) -> acc + x + "\n");
        }
    }

    public void saveToStorage() {
        try {
            storage.saveTaskList(this);
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}
