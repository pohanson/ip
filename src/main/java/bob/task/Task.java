package bob.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import bob.exception.InvalidInputException;

/**
 * Task must be inherited from.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task.
     * 
     * @param description description for the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Converts the task back to how the user input it.
     * 
     * @return the string representation of the task
     */
    abstract String toInputString();

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    /**
     * Gets the status icon based on its `isDone`
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public Task markDone() {
        this.isDone = true;
        return this;
    }

    /**
     * Marks the task as not done.
     */
    public Task unmarkDone() {
        this.isDone = false;
        return this;
    }

    /**
     * Creates and returns the correct Task subclass object from an input string.
     * 
     * @param input input string given. Should contains the task type.
     * @throws InvalidInputException
     */
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
     * Parses an input string in the format of "dd/MM/yyyy HHmm".
     * 
     * Example of a valid input string: "27/08/2025 1000"
     * 
     * @param input the date string to parse
     */
    public static LocalDateTime parseDateTimeString(String input) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return LocalDateTime.parse(input, dateFormat);
    }

    /**
     * Formats a LocalDateTime object into a string.
     * 
     * Example of the returned string: "27 Aug 2025 10:00"
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        return dateTime.format(dateFormat);
    }

    /**
     * Formats a LocalDateTime object into the input string.
     * 
     * Example of the returned string: "27/08/2025 1000"
     */
    public static String toInputStringDateTime(LocalDateTime dateTime) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return dateTime.format(dateFormat);
    }
}
