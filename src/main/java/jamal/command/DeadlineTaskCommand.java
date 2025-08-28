package jamal.command;

import jamal.task.Deadline;
import jamal.task.Task;
import jamal.task.TaskList;
import jamal.ui.Ui;
import jamal.util.Storage;

/**
 * Deadline Command SubClass for deadline task creation
 */

public class DeadlineTaskCommand extends Command {

    protected Deadline task;

    public DeadlineTaskCommand(Task task) {
        this.task = (Deadline) task;
    }

    /**
     * Executes Command on TaskList
     * Writes an unmarked Deadline data into Storage
     *
     * @param taskList Tasklist that contains an Arraylist of tasks
     * @param ui User interface for print statements
     * @param storage Stores data and allow read write operations on it
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        storage.addLine("D`UM`" + task.getDescription() + "`" + task.getBy());
    }
}