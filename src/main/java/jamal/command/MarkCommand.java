package jamal.command;

import jamal.task.TaskList;
import jamal.util.Storage;

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
     * @param storage Stores data and allow read write operations on it
     * @return String of actionable
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        String result = taskList.mark(this.idx);
        storage.markLine(this.idx);
        return result;
    }
}
