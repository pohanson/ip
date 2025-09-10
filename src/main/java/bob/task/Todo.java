package bob.task;

import bob.exception.InvalidInputException;

/**
 * Tasks without any date/time attached to it.
 */
public class Todo extends Task {
    /**
     * Constructs Todos.
     *
     * @param description description for the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Parses the input string given into ToDos object.
     *
     * @param input should be of the format: todo [description]
     */
    public static Todo parse(String input) throws InvalidInputException {
        String taskName = input.replaceFirst("todo", "").trim();
        if (taskName.isEmpty()) {
            throw new InvalidInputException("Invalid todo task (description cannot be empty): " + input
                    + "\nExample of valid format: 'todo borrow book'");
        }
        return new Todo(taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toInputString() {
        return "todo " + this.description;
    }
}
