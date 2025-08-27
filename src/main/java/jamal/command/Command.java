package jamal.command;

import jamal.task.*;
import jamal.ui.Ui;
import jamal.util.Storage;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}
