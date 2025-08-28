package jamal.command;

import jamal.task.TaskList;
import jamal.util.Storage;
import jamal.ui.Ui;

/**
 * Exit Command SubClass for deleting tasks
 */
public class ExitCommand extends Command {

    /**
     * Executes Command ui for exit statement
     *
     * @param taskList Tasklist that contains an Arraylist of tasks
     * @param ui User interface for print statements
     * @param storage Stores data and allow read write operations on it
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showExit();
    }

    /**
     * Returns true
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
