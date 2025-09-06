package bob.parser;

import bob.command.AddCommand;
import bob.command.Command;
import bob.command.DeleteCommand;
import bob.command.ExitCommand;
import bob.command.FindCommand;
import bob.command.InvalidCommand;
import bob.command.ListCommand;
import bob.command.MarkCommand;
import bob.command.UnmarkCommand;

/**
 * Parser parses user input into commands.
 */
public class Parser {

    /**
     * Parses user input into a command.
     *
     * @param input the user input provided, including the action word (eg.
     *              list, bye, mark, etc.)
     * @return the command according to the input. If the command is invalid, it
     *         will return an InvalidCommand.
     */
    public static Command parse(String input) {
        String[] parts = input.split(" ", 2);
        switch (parts[0].toLowerCase()) {
        case "list":
            return new ListCommand();
        case "bye":
            return new ExitCommand();
        case "mark": {
            if (parts.length != 2) {
                return new InvalidCommand(input, "The mark command requires a task number.");
            }
            int taskNumber = Integer.parseInt(parts[1]) - 1;
            return new MarkCommand(taskNumber);
        }
        case "unmark": {
            if (parts.length != 2) {
                return new InvalidCommand(input, "The unmark command requires a task number.");
            }
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
            if (parts.length != 2) {
                return new InvalidCommand(input, "The delete command requires a task number.");
            }
            int taskNumber = Integer.parseInt(parts[1]) - 1;
            return new DeleteCommand(taskNumber);
        }
        case "find": {
            if (parts.length != 2) {
                return new InvalidCommand(input, "The find command requires a search keyword.");
            }
            return new FindCommand(parts[1]);
        }
        default: {
            return new InvalidCommand(input);
        }
        }
    }
}
