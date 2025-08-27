public class Task {
    protected boolean isDone;
    protected String description;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatus() {
        return (this.isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    /// assume defaults for todo
    public boolean isOngoing() {
        return true;
    }

    public boolean isUpcoming() {
        return false;
    }

    public boolean isOverdue() {
        return false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatus() + "] " + description;
    }
}