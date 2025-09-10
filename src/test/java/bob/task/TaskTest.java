package bob.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import bob.exception.InvalidInputException;

public class TaskTest {
    @Test
    public void createFromString_validTodo_success() throws InvalidInputException {
        String input = "todo read";
        Task task = Task.createFromString(input);
        assertEquals(Todo.class, task.getClass());
        assertEquals("[T][ ] read", task.toString());
    }

    @Test
    public void createFromString_invalidTodo_exceptionThrown() {
        try {
            Task.createFromString("todo ");
            fail();
        } catch (InvalidInputException e) {
            assertTrue(e.getMessage().contains("Invalid todo task"));
        }
    }

    @Test
    public void createFromString_validDeadline_success() throws InvalidInputException {
        String input = "deadline return book /by 01/01/2025 1200";
        Task task = Task.createFromString(input);
        assertEquals(Deadline.class, task.getClass());
        assertEquals("[D][ ] return book (due: 01 Jan 2025 12:00)", task.toString());
    }

    @Test
    public void createFromString_invalidDeadline_exceptionThrown() {
        try {
            Task.createFromString("deadline taskname");
            fail();
        } catch (InvalidInputException e) {
            assertTrue(e.getMessage().contains("Invalid deadline input"));
        }
    }

    @Test
    public void createFromString_validEvent_success() throws InvalidInputException {
        String input = "event project meeting /from 01/01/2025 1200 /to 01/01/2025 1400";
        Task task = Task.createFromString(input);
        assertEquals(Event.class, task.getClass());
        assertEquals("[E][ ] project meeting (01 Jan 2025 12:00 - 01 Jan 2025 14:00)", task.toString());
    }

    @Test
    public void createFromString_invalidEvent_exceptionThrown() {
        try {
            Task.createFromString("event project meeting");
            fail();
        } catch (InvalidInputException e) {
            assertTrue(e.getMessage().contains("Invalid event input"));
        }
    }

    @Test
    public void createFromString_invalidTaskType_exceptionThrown() {
        try {
            Task.createFromString("task taskname");
            fail();
        } catch (InvalidInputException e) {
            assertTrue(e.getMessage().contains("Invalid task type"));
        }
    }
}
