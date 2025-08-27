package jamal.ui;

import java.util.Scanner;

public class Ui {

    final static String lineBreak = "____________________________________________________________\n";
    protected Scanner inputScanner;


    public Ui() {
        this.inputScanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println(
                lineBreak +
                "Yo! I'm Jamal\n" +
                "What can I do for ya?\n");
    }

    public void showExit() {
        System.out.println(lineBreak + "Catch ya later!\n");
    }

    public void showLine() {
        System.out.println(lineBreak);
    }

    public String readCommand() {
        return inputScanner.nextLine();
    }

    public void showLoadingError() {
        System.out.println("Error loading");
    }

    public void showError(String message) {
        System.out.println(message);
    }


}
