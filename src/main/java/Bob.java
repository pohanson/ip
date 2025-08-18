import java.util.Scanner;

public class Bob {
    public static void printSection(String s) {
        System.out.println("=".repeat(80));
        System.out.println(s);
        System.out.println("=".repeat(80));
    }

    public static String prompt(Scanner s) {
        System.out.print("> ");
        return s.nextLine();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printSection("Hello! I'm Bob.\nHow can I help you?");
        String userInput = "";
        while (true) {
            userInput = prompt(scanner);
            if(userInput.equals("bye")) {
                break;
            }
            printSection(userInput);
        }
        printSection("Bye. Hope to see you again soon!");
    }
}
