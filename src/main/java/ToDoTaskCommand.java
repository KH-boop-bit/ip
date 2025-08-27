public class ToDoTaskCommand extends Command{

    protected Task task;

    public ToDoTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        storage.addLine("T`UM`" + task.description);
    }
}
