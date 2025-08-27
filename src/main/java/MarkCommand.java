public class MarkCommand extends Command{

    protected int idx;

    public MarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.mark(this.idx);
        storage.markLine(this.idx);
    }
}