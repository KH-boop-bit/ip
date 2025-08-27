package jamal.command;

import jamal.task.Task;
import jamal.task.TaskList;
import jamal.util.Storage;
import jamal.ui.Ui;

public class ToDoTaskCommand extends Command {

    protected Task task;

    public ToDoTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        storage.addLine("T`UM`" + task.getDescription());
    }
}
