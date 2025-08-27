package jamal.command;

import jamal.task.Deadline;
import jamal.task.Task;
import jamal.task.TaskList;
import jamal.ui.Ui;
import jamal.util.Storage;

public class DeadlineTaskCommand extends Command {

    protected Deadline task;

    public DeadlineTaskCommand(Task task) {
        this.task = (Deadline) task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        storage.addLine("D`UM`" + task.getDescription() + "`" + task.getBy());
    }
}