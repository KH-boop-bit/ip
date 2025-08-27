public class DeadlineTaskCommand extends Command {

    protected Deadline task;

    public DeadlineTaskCommand(Task task) {
        this.task = (Deadline) task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        storage.addLine("D`UM`" + task.description + "`" + task.by);
    }
}
