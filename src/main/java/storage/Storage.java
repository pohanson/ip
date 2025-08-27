package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import exception.InvalidInputException;
import task.Task;
import task.TaskList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        Path path = Paths.get(filePath);
        if (!path.toFile().exists()) {
            try {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }
    }

    private File getFile() throws IOException {
        return Paths.get(this.filePath).toFile();
    }

    public void saveTaskList(TaskList tasks) throws IOException {
        File file = this.getFile();
        FileWriter fw = new FileWriter(file);
        try {
            fw.write(tasks.toStorageString());
        } finally {
            fw.close();
        }
    }

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
