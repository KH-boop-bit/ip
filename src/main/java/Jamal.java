import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner; //import scanner class for inputs
import java.util.regex.*; //import regex for matching words

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

        new Jamal("data/Jamal.txt").run();
    }
}
