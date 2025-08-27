public class Ui {

    public void printLine() {
        System.out.println("=".repeat(80));
    }

    public void showError(String message) {
        System.out.println("\u001B[31mError: " + message + "\u001B[0m");
    }

    public void printSection(String s) {
        System.out.println(s);
        this.printLine();
    }

    public void printHelp() {
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
}
