package jamal.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import jamal.util.DateTime;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, String by) throws DateTimeParseException{
        super(description);
        try {
            this.by = LocalDateTime.parse(by);
        } catch (DateTimeParseException e) {
            throw e; //prevent creation of deadline object for snowballing errors
        }

    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTime.formatDateTime(by) + ")";
    }

    @Override
    public boolean isOngoing() {
        return DateTime.isOngoing(by);
    }

    @Override
    public boolean isOverdue() {
        return DateTime.isOverdue(by);
    }

    public LocalDateTime getBy() {
        return this.by;
    }
}