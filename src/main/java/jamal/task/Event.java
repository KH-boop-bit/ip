package jamal.task;

import jamal.util.DateTime;
import java.time.LocalDateTime;

public class Event extends Task {

    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = LocalDateTime.parse(start);
        this.end = LocalDateTime.parse(end);
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