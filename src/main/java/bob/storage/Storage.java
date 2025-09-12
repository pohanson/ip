package bob.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import bob.exception.InvalidInputException;
import bob.task.Task;
import bob.task.TaskList;

/**
 * Storage handles loading tasklist from a file and saving it.
 */
public class Storage {
    // not declared as constant so that I could use a different file path
    // if needed.
    private final String filePath;

    /**
     * Constructs Storage.
     * <p>
     * It will also create relevant directories, if it does not exists.
     *
     * @param filePath the file path of the storage.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        Path path = Paths.get(filePath);

        // create file if it doesn't exist
        if (!path.toFile().exists()) {
            try {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }

        assert path.toFile().exists() : "File should exist after constructor";
    }

    /**
     * Gets the file based on filePath provided in constructor.
     */
    private File getFile() throws IOException {
        return Paths.get(this.filePath).toFile();
    }

    /**
     * Saves the task list to the file.
     *
     * @param tasks the task list to save.
     * @throws IOException when file could not be written to.
     */
    public void saveTaskList(TaskList tasks) throws IOException {
        File file = this.getFile();
        FileWriter fw = new FileWriter(file);
        try {
            fw.write(tasks.toStorageString());
        } finally {
            fw.close();
        }
    }

    /**
     * Loads the task list from the file.
     *
     * @throws IOException           when file could not be read from.
     * @throws InvalidInputException when file could not be parsed properly.
     */
    public TaskList loadTaskList() throws IOException, InvalidInputException {
        File file = this.getFile();
        Scanner s = new Scanner(file);
        try {
            TaskList tasks = new TaskList(this);
            while (s.hasNextLine()) {
                String[] line = s.nextLine().split(" ", 2);

                if (line.length != 2) {
                    System.out.println("Invalid task format in storage: " + line);
                    continue;
                }

                Task task = Task.createFromString(line[1]);
                if (line[0].equals("1")) {
                    task.markDone();
                }
                tasks.add(task);
            }
            return tasks;

        } finally {
            s.close();
        }
    }
}
