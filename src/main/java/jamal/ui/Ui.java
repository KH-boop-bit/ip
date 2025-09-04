package jamal.ui;

import java.util.Scanner;

/**
 *
 * Initialise Scanner to detect inputs
 * Print statements for Welcome, Exit and Linebreaks
 *
 */
public class Ui {

    final static String lineBreak = "____________________________________________________________\n";
    protected Scanner inputScanner;


    public Ui() {
        this.inputScanner = new Scanner(System.in);
    }

    /**
     * Prints welcome statement
     */
    public void showWelcome() {
        System.out.println(
                lineBreak +
                "Yo! I'm Jamal\n" +
                "What can I do for ya?\n");
    }

    /**
     * Prints exit statement
     */
    public void showExit() {
        System.out.println(lineBreak + "Catch ya later!\n");
    }

    /**
     * Prints a single line
     */
    public void showLine() {
        System.out.println(lineBreak);
    }

    /**
     * Waits for input and scans the input as a string for parsing
     *
     * @return String of scanned input
     */
    public String readCommand() {
        return inputScanner.nextLine();
    }

    /**
     * Prints loading error statement
     */
    public void showLoadingError() {
        System.out.println("Error loading");
    }

    /**
     * Prints error message
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Prints message body
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

}
