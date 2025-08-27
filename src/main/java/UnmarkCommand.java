public class UnmarkCommand extends Command {

    protected int idx;

    public UnmarkCommand(int idx) {
        this.idx = idx;
    }


    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.unmark(this.idx);
        storage.unmarkLine(this.idx);
    }
}
