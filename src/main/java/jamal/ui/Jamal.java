package jamal.ui;

import jamal.command.Command;
import jamal.task.TaskList;
import jamal.util.Parser;
import jamal.util.Storage;

/**
 * Main App
 * Run Jamal chatbot from here
 *
 */
public class Jamal {

    /**
     * Storage stores filepath for task data
     * Tasklist stores an Arraylist of task to reference
     * Ui stores methods for input scanning and print statements
     */
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Jamal(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.load());
    }

    /**
     * Runs the program
     * Initialises Ui scanner for input detection
     * Converts input into Commands by parsing input
     * Executes Commands while referencing tasklist, ui and storage
     *
     */
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

    /**
     * Runs the program
     */
    public static void main(String[] args) {

        new Jamal("data/jamal.ui.Jamal.txt").run();
    }
}
