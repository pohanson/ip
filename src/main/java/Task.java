public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public static Task createFromString(String input) throws InvalidInputException {
        if (input.startsWith("todo")) {
            return ToDos.parse(input);
        }
        if (input.startsWith("deadline")) {
            return Deadlines.parse(input);
        }
        throw new InvalidInputException("Invalid task type: " + input + "\nValid types are: todo");
    }

}
