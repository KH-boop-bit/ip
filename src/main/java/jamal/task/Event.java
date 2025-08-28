package jamal.task;

import jamal.util.DateTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String description, String start, String end) throws DateTimeParseException {
        super(description);
        try {
            this.start = LocalDateTime.parse(start);
            this.end = LocalDateTime.parse(end);
        } catch (DateTimeParseException e) {
            throw e; //prevent creation of event object for snowballing errors
        }

    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateTime.formatDateTime(start) + " to: " + DateTime.formatDateTime(end) + ")";
    }

    @Override
    public boolean isOngoing() {
        return DateTime.isOngoing(start, end);
    }

    @Override
    public boolean isUpcoming() {
        return DateTime.isUpcoming(start);
    }

    @Override
    public boolean isOverdue() {
        return DateTime.isOverdue(end);
    }

    public LocalDateTime getStart() {
        return this.start;
    }

    public LocalDateTime getEnd() {
        return this.end;
    }
}