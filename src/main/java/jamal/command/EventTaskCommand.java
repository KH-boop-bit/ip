package jamal.command;

import jamal.task.Event;
import jamal.task.Task;
import jamal.task.TaskList;
import jamal.util.Storage;
import jamal.ui.Ui;

/**
 * Event Command SubClass for event task creation
 */
public class EventTaskCommand extends Command {

    protected Event task;

    public EventTaskCommand(Task task) {
        this.task = (Event) task;
    }

    /**
     * Executes Command on TaskList
     * Writes an unmarked Event data into Storage
     *
     * @param taskList Tasklist that contains an Arraylist of tasks
     * @param ui User interface for print statements
     * @param storage Stores data and allow read write operations on it
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        storage.addLine("E`UM`" + task.getDescription() + "`" + task.getStart() + "`" + task.getEnd());
    }
}