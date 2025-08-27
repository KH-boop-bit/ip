import java.time.LocalDateTime;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by);
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
}