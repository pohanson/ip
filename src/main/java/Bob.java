import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Bob {
    public static ArrayList<String> tasks = new ArrayList<>(100);

    public static void printSection(String s) {
        System.out.println(s);
        System.out.println("=".repeat(80));
    }

    public static String prompt(Scanner s) {
        System.out.print("> ");
        return s.nextLine();
    }

    private static void printTasks() {
        printSection(
                IntStream.range(0, tasks.size())
                        .mapToObj(i -> String.format("%d. %s", i + 1, tasks.get(i)))
                        .reduce("", (acc, cur) -> acc + "\n\t" + cur));
    }

    private static void addTasks(String task) {
        tasks.add(task);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printSection("Hello! I'm Bob.\nHow can I help you?");
        String userInput;
        do {
            userInput = prompt(scanner);
            switch (userInput) {
                case "bye": {
                    break;
                }
                case "list": {
                    printTasks();
                    break;
                }
                default: {
                    addTasks(userInput);
                    printSection("added: " + userInput);
                }
            }
        } while (!userInput.equals("bye"));
        printSection("Bye. Hope to see you again soon!");
    }
}
