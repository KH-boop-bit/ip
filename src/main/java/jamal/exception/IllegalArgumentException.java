package jamal.exception;

/**
 * Throws an Illegal Argument Exception if Argument is unrecognised by the method
 */
public class IllegalArgumentException extends Exception {

    public IllegalArgumentException() {
        super("Argument entered is not recognised. Please try again!");
    }
}
