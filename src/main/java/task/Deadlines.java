package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import exception.InvalidInputException;

public class Deadlines extends Task {
    private LocalDateTime deadline;

    public Deadlines(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (due: %s)", super.toString(), Task.formatDateTime(deadline));
    }

    public static Deadlines parse(String input) throws InvalidInputException {
        String[] params = input.replaceFirst("deadline", "").split("/by");
        if (params.length != 2 || params[0].trim().isEmpty() || params[1].trim().isEmpty()) {
            throw new InvalidInputException(
                    "Invalid deadline input: " + input
                            + "\nExample of valid format: deadline return book /by 01/01/2025 1200");
        }
        try {
            LocalDateTime deadline = Task.parseDateTimeString(params[1].trim());
            return new Deadlines(params[0].trim(), deadline);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Invalid date format in deadline input: " + params[1].trim()
                    + "\nExample of valid format: deadline return book /by 01/01/2025 1200");
        }

    }

    public String toInputString() {
        return "deadline " + this.description + " /by " + Task.toInputStringDateTime(this.deadline);
    }
}
