package jamal.command;

import jamal.task.*;
import jamal.ui.Ui;
import jamal.util.Storage;
/**
 * Abstract Parent Command Class
 */

public abstract class Command {


    /**
     * Executes Command on TaskList
     *
     * @param tasks Tasklist that contains an Arraylist of tasks
     * @param ui User interface for print statements
     * @param storage Stores data and allow read write operations on it
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns true if Command type is ExitCommand
     * If Command is not ExitCommand, returns false
     *
     * @return type ExitCommand
     */
    public boolean isExit() {
        return false;
    }
}
