public class Events extends Task {
    private String start;
    private String end;

    public Events(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), start, end);
    }

    public static Events parse(String input) throws InvalidInputException {
        String[] params = input.replaceFirst("event", "").split("/from|/to");
        if (params.length != 3 || params[0].trim().isEmpty() || params[1].trim().isEmpty()
                || params[2].trim().isEmpty()) {
            throw new InvalidInputException(
                    "Invalid event input: " + input
                            + "\nExample of valid format: event project meeting /from Mon 2pm /to 4pm");
        }
        return new Events(params[0].trim(), params[1].trim(), params[2].trim());
    }

    public String toInputString() {
        return "event " + this.description + " /from " + this.start + " /to " + this.end;
    }
}
