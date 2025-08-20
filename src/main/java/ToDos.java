public class ToDos extends Task {
    public ToDos(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public static ToDos parse(String input) throws InvalidInputException {
        String taskName = input.replaceFirst("todo", "").trim();
        if (taskName.isEmpty()) {
            throw new InvalidInputException(
                    "Invalid todo task: " + input + "\nExample of valid format: 'todo borrow book'");
        }
        return new ToDos(taskName);
    }
}
