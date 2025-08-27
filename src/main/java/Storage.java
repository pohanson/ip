import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {
    private static final String FILENAME = "data/tasklist.txt";

    public Storage() {
    }

    private static File getFile() throws IOException {
        File file = Paths.get(FILENAME).toFile();
        if (!file.isFile()) {
            System.out.println("Creating new file at " + file.getAbsolutePath());
            file.createNewFile();
        }
        return file;
    }

    public static void saveTaskList(TaskList tasks) throws IOException {
        try {
            Files.createDirectory(Paths.get("data"));
        } catch (FileAlreadyExistsException e) {
        }
        File file = getFile();
        FileWriter fw = new FileWriter(file);
        try {
            fw.write(tasks.toStorageString());
        } finally {
            fw.close();
        }
    }

    public static TaskList loadTaskList() throws IOException, InvalidInputException {
        File file = getFile();
        Scanner s = new Scanner(file);
        try {
            TaskList tasks = TaskList.fromStorage(s);
            return tasks;
        } finally {
            s.close();
        }
    }
}
