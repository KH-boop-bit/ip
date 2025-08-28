package jamal.command;

import jamal.task.TaskList;
import jamal.util.Storage;
import jamal.ui.Ui;

/**
 * Mark Command SubClass for marking tasks
 */
public class MarkCommand extends Command {

    protected int idx;

    public MarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Executes Command on TaskList
     * Rewrite data at idx line in Storage to mark
     *
     * @param taskList Tasklist that contains an Arraylist of tasks
     * @param ui User interface for print statements
     * @param storage Stores data and allow read write operations on it
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.mark(this.idx);
        storage.markLine(this.idx);
    }
}