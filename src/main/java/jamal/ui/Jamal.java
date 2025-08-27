package jamal.ui;

import jamal.command.Command;
import jamal.task.TaskList;
import jamal.util.Parser;
import jamal.util.Storage;


public class Jamal {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Jamal(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.showWelcome();
        ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {

        new Jamal("data/jamal.ui.Jamal.txt").run();
    }
}
