package bob.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import bob.exception.InvalidInputException;

/**
 * Tasks that need to be done before a specific date/time.
 */
public class Deadline extends Task {
    private final LocalDateTime deadline;

    /**
     * Constructs Deadlines.
     *
     * @param description description for the task.
     * @param deadline    deadline for the task.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Parses the input string given into Deadlines object.
     *
     * @param input should be of the format: deadline [description] /by
     *              [datetime].
     */
    public static Deadline parse(String input) throws InvalidInputException {
        String[] params = input.replaceFirst("deadline", "").split("/by");
        if (params.length != 2 || params[0].trim().isEmpty() || params[1].trim().isEmpty()) {
            throw new InvalidInputException("Invalid deadline input: " + input
                    + "\nExample of valid format: deadline return book /by 01/01/2025 1200");
        }
        try {
            LocalDateTime deadline = Task.parseDateTimeString(params[1].trim());
            return new Deadline(params[0].trim(), deadline);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Invalid date format in deadline input: " + params[1].trim()
                    + "\nExample of valid format: deadline return book /by 01/01/2025 1200");
        }

    }

    @Override
    public String toString() {
        return String.format("[D]%s (due: %s)", super.toString(), Task.formatDateTime(deadline));
    }

    @Override
    public String toInputString() {
        return "deadline " + this.description + " /by " + Task.toInputStringDateTime(this.deadline);
    }
}
