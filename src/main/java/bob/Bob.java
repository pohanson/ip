package bob;

import java.io.IOException;
import java.util.Scanner;

import bob.command.Command;
import bob.command.ExitCommand;
import bob.exception.InvalidInputException;
import bob.parser.Parser;
import bob.storage.Storage;
import bob.task.TaskList;
import bob.ui.Ui;

public class Bob {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    public Bob(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        try {
            tasks = this.storage.loadTaskList();
        } catch (IOException e) {
            ui.showError("Error loading tasks from file, using empty task list: " + e.getMessage());
            tasks = new TaskList(this.storage);
        } catch (InvalidInputException e) {
            ui.showError("Error loading tasks from file, using empty task list: " + e.getMessage());
            tasks = new TaskList(this.storage);
        }

    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.printSection("Hello! I'm Bob.\nHow can I help you?");
        while (true) {
            System.out.print("> ");
            Command command = Parser.parse(scanner.nextLine());
            command.execute(tasks, ui, storage);
            if (command instanceof ExitCommand) {
                break;
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        new Bob("data/tasklist.txt").run();
    }
}
