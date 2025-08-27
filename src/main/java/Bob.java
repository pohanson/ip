import java.io.IOException;
import java.util.Scanner;

import exception.InvalidInputException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

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

    enum ActionType {
        LIST,
        EXIT,
        MARK,
        UNMARK,
        ADD,
        DELETE,
        NULL,
        INVALID
    }

    private static class ActionData {
        public final ActionType action;
        public final String data;

        public ActionData(ActionType action, String data) {
            this.action = action;
            this.data = data;
        }
    }

    private static ActionData prompt(Scanner s) {
        System.out.print("> ");
        String firstWord = s.next();
        String restOfLine = s.nextLine().trim();

        ActionType action;
        switch (firstWord) {
            case "list": {
                action = ActionType.LIST;
                break;
            }
            case "bye": {
                action = ActionType.EXIT;
                break;
            }
            case "mark": {
                action = ActionType.MARK;
                break;
            }
            case "unmark": {
                action = ActionType.UNMARK;
                break;
            }
            case "todo":
            case "deadline":
            case "event": {
                action = ActionType.ADD;
                restOfLine = firstWord + " " + restOfLine;
                break;
            }
            case "delete": {
                action = ActionType.DELETE;
                break;
            }
            default: {
                action = ActionType.INVALID;
                restOfLine = firstWord + " " + restOfLine;
            }
        }

        return new ActionData(action, restOfLine);
    }

    private void executeAction(Bob.ActionData actionData) {
        switch (actionData.action) {
            case LIST:
                ui.printSection(tasks.stringifyTasks());
                break;
            case EXIT:
                break;
            case MARK: {
                int index = Integer.parseInt(actionData.data) - 1;
                if (tasks.validateTaskIndex(index)) {
                    tasks.markDone(index);
                    ui.printSection("I've marked this task as done:\n\t" + tasks.get(index));
                } else {
                    ui.printSection("Invalid task number: " + actionData.data);
                }
                break;
            }
            case UNMARK: {
                int index = Integer.parseInt(actionData.data) - 1;
                if (tasks.validateTaskIndex(index)) {
                    Task task = tasks.unmarkDone(index);
                    ui.printSection("I've marked this task as not done yet:\n\t" + task);
                } else {
                    ui.printSection("Invalid task number: " + actionData.data);
                }
                break;
            }
            case ADD:
                try {
                    Task task = Task.createFromString(actionData.data);
                    tasks.add(task);
                    ui.printSection(
                            String.format("I've added this task:\n\t %s\nNow you've %d tasks in the list.", task,
                                    tasks.size()));
                } catch (InvalidInputException e) {
                    ui.printSection(e.getMessage());
                }
                break;
            case DELETE: {
                int index = Integer.parseInt(actionData.data) - 1;
                if (tasks.validateTaskIndex(index)) {
                    Task removedTask = tasks.remove(index);
                    ui.printSection(String.format("I've removed this task:\n\t%s\nNow you've %d tasks in the list.",
                            removedTask,
                            tasks.size()));
                } else {
                    ui.printSection("Invalid task number: " + actionData.data);
                }
                break;
            }
            case INVALID:
                ui.printSection("Invalid command: " + actionData.data);
                ui.printHelp();
                break;
            default:
                break;
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.printSection("Hello! I'm Bob.\nHow can I help you?");
        ActionData userInput = new ActionData(ActionType.NULL, "");
        do {
            userInput = prompt(scanner);
            executeAction(userInput);
        } while (userInput.action != ActionType.EXIT);
        ui.printSection("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        new Bob("data/tasklist.txt").run();
    }
}
