package jamal.command;

import jamal.task.TaskList;
import jamal.util.Storage;
import jamal.ui.Ui;

/**
 * Delete Command SubClass for deleting tasks
 */
public class DeleteCommand extends Command {

    protected int idx;

    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Executes Command on TaskList
     * Deletes line at idx line in datafile and rewrites the whole Storage file
     *
     * @param taskList Tasklist that contains an Arraylist of tasks
     * @param ui User interface for print statements
     * @param storage Stores data and allow read write operations on it
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.delete(this.idx);
        storage.deleteLine(this.idx);
    }
}