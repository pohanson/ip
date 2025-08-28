package bob.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import bob.exception.InvalidInputException;

public class Events extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    public Events(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (%s - %s)", super.toString(), super.formatDateTime(start),
                super.formatDateTime(end));
    }

    public static Events parse(String input) throws InvalidInputException {
        String[] params = input.replaceFirst("event", "").split("/from|/to");
        if (params.length != 3 || params[0].trim().isEmpty() || params[1].trim().isEmpty()
                || params[2].trim().isEmpty()) {
            throw new InvalidInputException(
                    "Invalid event input: " + input
                            + "\nExample of valid format: event project meeting /from 01/01/2025 1200 /to 01/01/2025 1400");
        }

        try {
            LocalDateTime start = Task.parseDateTimeString(params[1].trim());
            LocalDateTime end = Task.parseDateTimeString(params[2].trim());
            return new Events(params[0].trim(), start, end);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException(
                    "Invalid date format in event input: " + input
                            + "\nExample of valid format: event project meeting /from 01/01/2025 1200 /to 01/01/2025 1400");
        }
    }

    public String toInputString() {
        return "event " + this.description + " /from " + super.toInputStringDateTime(this.start) + " /to "
                + super.toInputStringDateTime(this.end);
    }
}
