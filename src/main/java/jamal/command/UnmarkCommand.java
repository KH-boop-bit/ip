package jamal.command;

import jamal.task.TaskList;
import jamal.util.Storage;
import jamal.ui.Ui;

/**
 * Unmark Command SubClass for unmarking tasks
 */
public class UnmarkCommand extends Command {

    protected int idx;

    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Executes Command on TaskList
     * Rewrite data at idx line in Storage to unmark
     *
     * @param taskList Tasklist that contains an Arraylist of tasks
     * @param storage Stores data and allow read write operations on it
     * @return String of actionable
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        String result = taskList.unmark(this.idx);
        storage.unmarkLine(this.idx);
        return result;
    }
}
