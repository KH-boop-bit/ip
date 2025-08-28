package jamal.command;

import jamal.task.Task;
import jamal.task.TaskList;
import jamal.util.Storage;
import jamal.ui.Ui;

/**
 * Deadline Command SubClass for todo task creation
 */
public class ToDoTaskCommand extends Command {

    protected Task task;

    public ToDoTaskCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes Command on TaskList
     * Writes an unmarked Todo data into Storage
     *
     * @param taskList Tasklist that contains an Arraylist of tasks
     * @param ui User interface for print statements
     * @param storage Stores data and allow read write operations on it
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        storage.addLine("T`UM`" + task.getDescription());
    }
}
