package jamal.command;

import jamal.task.TaskList;
import jamal.util.Storage;
import jamal.ui.Ui;

/**
 * Find Command Subclass of Command
 * Finds
 *
 */
public class FindCommand extends Command {

    protected String match;

    public FindCommand(String match) {
        this.match = match;
    }

    /**
     * Executes Command on TaskList
     * Finds tasks with description containing match
     *
     * @param taskList Tasklist that contains an Arraylist of tasks
     * @param ui User interface for print statements
     * @param storage Stores data and allow read write operations on it
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.find(this.match);
    }
}