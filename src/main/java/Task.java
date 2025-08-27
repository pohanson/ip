public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    abstract String toInputString();

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public Task markDone() {
        this.isDone = true;
        return this;
    }

    public Task unmarkDone() {
        this.isDone = false;
        return this;
    }

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
}
