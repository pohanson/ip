package bob.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bob.command.AddCommand;
import bob.command.Command;
import bob.command.DeleteCommand;
import bob.command.ExitCommand;
import bob.command.ListCommand;
import bob.command.MarkCommand;
import bob.command.UnmarkCommand;

public class ParserTest {
    @Test
    public void parse_listCommand_success() {
        Command command = Parser.parse("list");
        assertEquals(ListCommand.class, command.getClass());

    }

    @Test
    public void parse_byeCommand_success() {
        Command command = Parser.parse("bye");
        assertEquals(ExitCommand.class, command.getClass());
    }

    @Test
    public void parse_markCommand_success() {
        Command command = Parser.parse("mark 1");
        assertEquals(MarkCommand.class, command.getClass());
    }

    @Test
    public void parse_unmarkCommand_success() {
        Command command = Parser.parse("unmark 1");
        assertEquals(UnmarkCommand.class, command.getClass());
    }

    @Test
    public void parse_todoCommand_success() {
        Command command = Parser.parse("todo read");
        assertEquals(AddCommand.class, command.getClass());
    }

    @Test
    public void parse_deadlineCommand_success() {
        Command command = Parser.parse("deadline return book /by 01/01/2025 1200");
        assertEquals(AddCommand.class, command.getClass());
    }

    @Test
    public void parse_eventCommand_success() {
        Command command = Parser.parse("event project meeting /from 01/01/2025 1200 /to 01/01/2025 1400");
        assertEquals(AddCommand.class, command.getClass());
    }

    @Test
    public void parse_deleteCommand_success() {
        Command command = Parser.parse("delete 1");
        assertEquals(DeleteCommand.class, command.getClass());
    }

    @Test
    public void parse_invalidCommand_success() {
        Command command = Parser.parse("blah");
        assertEquals(bob.command.InvalidCommand.class, command.getClass());
    }
}
