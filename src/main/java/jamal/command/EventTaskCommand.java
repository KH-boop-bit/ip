package jamal.command;

import jamal.task.Event;
import jamal.task.Task;
import jamal.task.TaskList;
import jamal.util.Storage;
import jamal.ui.Ui;

public class EventTaskCommand extends Command {

    protected Event task;

    public EventTaskCommand(Task task) {
        this.task = (Event) task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        storage.addLine("E`UM`" + task.getDescription() + "`" + task.getStart() + "`" + task.getEnd());
    }
}