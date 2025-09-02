package bob.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import bob.exception.InvalidInputException;

/**
 * Tasks that start at a specific date/time and ends at a specific date/time.
 */
public class Events extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructs Events.
     *
     * @param description description of the task
     * @param start       start time of the task
     * @param end         end time of the task
     */
    public Events(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Parses the input string given into Events object.
     *
     * @param input should be of the format: event [description] /from [start
     *              datetime] /to [end datetime]
     */
    public static Events parse(String input) throws InvalidInputException {
        String[] params = input.replaceFirst("event", "").split("/from|/to");
        if (params.length != 3 || params[0].trim().isEmpty() || params[1].trim().isEmpty()
                || params[2].trim().isEmpty()) {
            throw new InvalidInputException("Invalid event input: " + input
                    + "\nExample of valid format: event project meeting /from 01/01/2025 1200 /to 01/01/2025 1400");
        }

        try {
            LocalDateTime start = Task.parseDateTimeString(params[1].trim());
            LocalDateTime end = Task.parseDateTimeString(params[2].trim());
            return new Events(params[0].trim(), start, end);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Invalid date format in event input: " + input
                    + "\nExample of valid format: event project meeting /from 01/01/2025 1200 /to 01/01/2025 1400");
        }
    }

    @Override
    public String toString() {
        return String.format("[E]%s (%s - %s)", super.toString(), formatDateTime(start),
                formatDateTime(end));
    }

    @Override
    public String toInputString() {
        return "event " + this.description + " /from " + toInputStringDateTime(this.start) + " /to "
                + toInputStringDateTime(this.end);
    }
}
