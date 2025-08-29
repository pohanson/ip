package bob.parser;

import bob.command.AddCommand;
import bob.command.Command;
import bob.command.DeleteCommand;
import bob.command.ExitCommand;
import bob.command.InvalidCommand;
import bob.command.ListCommand;
import bob.command.MarkCommand;
import bob.command.UnmarkCommand;

public class Parser {
    public static Command parse(String input) {
        String[] parts = input.split(" ", 2);
        switch (parts[0].toLowerCase()) {
        case "list":
            return new ListCommand();
        case "bye":
            return new ExitCommand();
        case "mark": {
            int taskNumber = Integer.parseInt(parts[1]) - 1;
            return new MarkCommand(taskNumber);
        }
        case "unmark": {
            int taskNumber = Integer.parseInt(parts[1]) - 1;
            return new UnmarkCommand(taskNumber);
        }
        case "todo":
            // Fallthrough as they uses the Task.createFromString factory method
        case "deadline":
            // Fallthrough as they uses the Task.createFromString factory method
        case "event": {
            return new AddCommand(input);
        }
        case "delete": {
            int taskNumber = Integer.parseInt(parts[1]) - 1;
            return new DeleteCommand(taskNumber);
        }
        default: {
            return new InvalidCommand(input);
        }

        }
    }
}
