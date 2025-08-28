package bob.command;

import bob.storage.Storage;
import bob.task.TaskList;
import bob.ui.Ui;

public abstract class Command {
    abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
