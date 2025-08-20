public class Deadlines extends Task {
    private String deadline;

    public Deadlines(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }

    public static Deadlines parse(String input) throws InvalidInputException {
        String[] params = input.replaceFirst("deadline", "").split("/by");
        if (params.length != 2 || params[0].trim().isEmpty() || params[1].trim().isEmpty()) {
            throw new InvalidInputException(
                    "Invalid deadline input: " + input + "\nExample of valid format: deadline return book /by Sunday");
        }
        return new Deadlines(params[0].trim(), params[1].trim());

    }
}
