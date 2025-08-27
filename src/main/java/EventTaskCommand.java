public class EventTaskCommand extends Command {

    protected Event task;

    public EventTaskCommand(Task task) {
        this.task = (Event) task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        storage.addLine("E`UM`" + task.description + "`" + task.start + "`" + task.end);
    }
}