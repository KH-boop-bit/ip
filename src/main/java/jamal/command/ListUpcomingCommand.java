package jamal.command;

import jamal.task.TaskList;
import jamal.util.Storage;
import jamal.ui.Ui;

/**
 * List Upcoming Command SubClass for listing tasks
 */
public class ListUpcomingCommand extends Command {

    /**
     * Executes Command on TaskList
     *
     * @param taskList Tasklist that contains an Arraylist of tasks
     * @param storage Stores data and allow read write operations on it
     * @return String of actionable
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return taskList.listUpcoming();
    }
}
