package jamal.command;

import jamal.task.TaskList;
import jamal.util.Storage;
import jamal.ui.Ui;

/**
 * List Command SubClass for listing tasks
 */
public class ListCommand extends Command {

    /**
     * Executes Command on TaskList
     *
     * @param taskList Tasklist that contains an Arraylist of tasks
     * @param ui User interface for print statements
     * @param storage Stores data and allow read write operations on it
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.list();
    }
}
