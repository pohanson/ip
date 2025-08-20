import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Bob {
    private static final ArrayList<Task> tasks = new ArrayList<>(100);

    enum ActionType {
        LIST,
        EXIT,
        MARK,
        UNMARK,
        ADD,
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
            case "deadline": {
                action = ActionType.ADD;
                restOfLine = firstWord + " " + restOfLine;
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
                printTasks();
                break;
            case EXIT:
                break;
            case MARK: {
                int index = Integer.parseInt(actionData.data) - 1;
                if (validateTaskIndex(index)) {
                    tasks.get(index).markDone();
                    printSection("I've marked this task as done:\n\t" + tasks.get(index));
                } else {
                    printSection("Invalid task number: " + actionData.data);
                }
                break;
            }
            case UNMARK: {
                int index = Integer.parseInt(actionData.data) - 1;
                if (validateTaskIndex(index)) {
                    tasks.get(index).unmarkDone();
                    printSection("I've marked this task as not done yet:\n\t" + tasks.get(index));
                } else {
                    printSection("Invalid task number: " + actionData.data);
                }
                break;
            }
            case ADD:
                try {
                    Task task = Task.createFromString(actionData.data);
                    addTasks(task);
                    printSection(String.format("I've added this task:\n\t %s\nNow you've %d tasks in the list.", task,
                            tasks.size()));
                } catch (InvalidInputException e) {
                    printSection(e.getMessage());
                }
                break;
            case INVALID:
                printSection("Invalid command: " + actionData.data);
                break;
            default:
                break;
        }
    }

    private static boolean validateTaskIndex(int index) {
        return (index >= 0 && index < tasks.size());

    }

    private static void printTasks() {
        printSection(
                IntStream.range(0, tasks.size())
                        .mapToObj(i -> String.format("%d. %s", i + 1, tasks.get(i)))
                        .reduce("", (acc, cur) -> acc + "\n\t" + cur));
    }

    private static void addTasks(Task task) {
        tasks.add(task);
    }

    public static void main(String[] args) {
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
