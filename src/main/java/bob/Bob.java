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

/**
 * Bob is the main class for the application.
 */
public class Bob {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Constructs Bob.
     *
     * It constructs and initialises tasks, storage and ui.
     *
     * @param filePath the file path to load tasks from and save tasks to.
     */
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

    /**
     * Entry point for the app.
     */
    public static void main(String[] args) {
        new Bob("data/tasklist.txt").run();
    }

    /**
     * Gets response from Bob for the given input.
     *
     * @param input the user input
     * @return the response from Bob
     */
    public String getResponse(String input) {
        Command command = Parser.parse(input);
        // Capture the output instead of printing it directly
        command.execute(tasks, ui, storage);
        return ui.getResponse();
    }

    /**
     * Main run loop for the application.
     */
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
}
