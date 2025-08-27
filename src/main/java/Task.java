import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    abstract String toInputString();

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public Task markDone() {
        this.isDone = true;
        return this;
    }

    public Task unmarkDone() {
        this.isDone = false;
        return this;
    }

    public static Task createFromString(String input) throws InvalidInputException {
        if (input.startsWith("todo")) {
            return ToDos.parse(input);
        }
        if (input.startsWith("deadline")) {
            return Deadlines.parse(input);
        }
        if (input.startsWith("event")) {
            return Events.parse(input);
        }
        throw new InvalidInputException("Invalid task type: " + input + "\nValid types are: todo");
    }

    /**
     * Parses an input string in the format of "dd/MM/yyyy HHmm", for example,
     * "27/08/2025 1000"
     * 
     * @param input the date string to parse
     */
    public static LocalDateTime parseDateTimeString(String input) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return LocalDateTime.parse(input, dateFormat);
    }

    /**
     * Formats a LocalDateTime object into a string. eg. "27 Aug 2025 10:00"
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        return dateTime.format(dateFormat);
    }

    /**
     * Formats a LocalDateTime object into the input string. eg. "27/08/2025 1000"
     */
    public static String toInputStringDateTime(LocalDateTime dateTime) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return dateTime.format(dateFormat);
    }
}
