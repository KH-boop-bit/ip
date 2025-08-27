public class ListUpcomingCommand extends Command{

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.listUpcoming();
    }
}
