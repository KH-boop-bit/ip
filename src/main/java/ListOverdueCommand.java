public class ListOverdueCommand extends Command{

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.listOverdue();
    }
}