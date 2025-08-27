import java.io.IOException;
import java.util.Scanner;

public class Bob {
    private static TaskList tasks;

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

    private static void printSection(String s) {
        System.out.println(s);
        System.out.println("=".repeat(80));
    }

    private static void printHelp() {
        System.out.println(
                """
                        Available commands:
                            list - List all tasks
                            mark <task number> - Mark a task as done
                            unmark <task number> - Unmark a task as not done
                            todo <description> - Add a task without any date/time attached to it
                            deadline <description> /by <datetime> - Add a task that need to be done before a specific date/time
                            event <description> /from <start datetime> /to <end datetime> - Add task that start at a specific date/time and ends at a specific date/time
                            delete <task number> - Delete a task from the list
                            bye - Exit the program
                        Note:
                            All datetime should be in the format of "dd/MM/yyyy HHmm", for example: 27/08/2025 1000
                            """);
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

    private static void executeAction(Bob.ActionData actionData) {
        switch (actionData.action) {
            case LIST:
                printSection(tasks.stringifyTasks());
                break;
            case EXIT:
                break;
            case MARK: {
                int index = Integer.parseInt(actionData.data) - 1;
                if (tasks.validateTaskIndex(index)) {
                    tasks.markDone(index);
                    printSection("I've marked this task as done:\n\t" + tasks.get(index));
                } else {
                    printSection("Invalid task number: " + actionData.data);
                }
                break;
            }
            case UNMARK: {
                int index = Integer.parseInt(actionData.data) - 1;
                if (tasks.validateTaskIndex(index)) {
                    Task task = tasks.unmarkDone(index);
                    printSection("I've marked this task as not done yet:\n\t" + task);
                } else {
                    printSection("Invalid task number: " + actionData.data);
                }
                break;
            }
            case ADD:
                try {
                    Task task = Task.createFromString(actionData.data);
                    tasks.add(task);
                    printSection(String.format("I've added this task:\n\t %s\nNow you've %d tasks in the list.", task,
                            tasks.size()));
                } catch (InvalidInputException e) {
                    printSection(e.getMessage());
                }
                break;
            case DELETE: {
                int index = Integer.parseInt(actionData.data) - 1;
                if (tasks.validateTaskIndex(index)) {
                    Task removedTask = tasks.remove(index);
                    printSection(String.format("I've removed this task:\n\t%s\nNow you've %d tasks in the list.",
                            removedTask,
                            tasks.size()));
                } else {
                    printSection("Invalid task number: " + actionData.data);
                }
                break;
            }
            case INVALID:
                printSection("Invalid command: " + actionData.data);
                printHelp();
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        try {
            tasks = Storage.loadTaskList();
        } catch (IOException e) {
            System.out.println("Error loading tasks from file, using empty task list: " + e.getMessage());
            tasks = new TaskList();
        } catch (InvalidInputException e) {
            System.out.println("Error loading tasks from file, using empty task list: " + e.getMessage());
            tasks = new TaskList();
        }

        Scanner scanner = new Scanner(System.in);
        printSection("Hello! I'm Bob.\nHow can I help you?");
        ActionData userInput = new ActionData(ActionType.NULL, "");
        do {
            userInput = prompt(scanner);
            executeAction(userInput);
        } while (userInput.action != ActionType.EXIT);
        printSection("Bye. Hope to see you again soon!");
    }
}
