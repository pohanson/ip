public class Bob {
    public static void printSection(String s) {
        System.out.println("=".repeat(80));
        System.out.println(s);
        System.out.println("=".repeat(80));
    }

    public static void main(String[] args) {
        printSection("Hello! I'm Bob.\nHow can I help you?");
        printSection("Bye. Hope to see you again soon!");
    }
}
