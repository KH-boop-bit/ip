public class DeleteCommand extends Command {

    protected int idx;

    public DeleteCommand(int idx) {
        this.idx = idx;
    }


    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.delete(this.idx);
        storage.deleteLine(this.idx);
    }
}