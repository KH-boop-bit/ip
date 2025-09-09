package jamal.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Tasklist contains an Arraylist of Task objects to perform command like operations on
 *
 * @return various print statements in accordance to type of command calling on method
 */
public class TaskList {

    protected ArrayList<Task> taskList;

    /**
     * Initialisation of Arraylist to store Task objects
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Initialisation of Arraylist to store Task objects from a list of datastring received from Storage or a datafile
     *
     * @param taskDataStrings list of string of formatted task data from Storage
     *
     */
    public TaskList(List<String> taskDataStrings) {
        this.taskList = new ArrayList<>();
        for (String line : taskDataStrings) {
            String[] temp = line.split("`");
            switch (temp[0]) {
            case "T" -> this.taskList.add(new ToDo(temp[2]));
            case "D" -> this.taskList.add(new Deadline(temp[2], temp[3]));
            case "E" -> this.taskList.add(new Event(temp[2], temp[3], temp[4]));
            default -> throw new IllegalArgumentException("Invalid Argument");
            }
        }

    }

    /**
     * Lists all tasks in tasklist, default by order of insertion
     *
     * @return statement of all tasks in tasklist
     */
    public String list() {
        StringBuilder listString = new StringBuilder();
        listString.append("Here are your tasks:\n");
        for (int i = 0; i < taskList.size(); i++) {
            listString.append(i + 1)
                    .append(". ")
                    .append(taskList.get(i).toString())
                    .append("\n");
        }
        return listString.toString();
    }

    /**
     * Lists all tasks in tasklist that are ongoing
     *
     * @return  statement of all tasks in tasklist that are ongoing, dependent on task typing
     */
    public String listOngoing() {
        StringBuilder listString = new StringBuilder();
        listString.append("These are your ongoing tasks:\n");
        taskList.stream()
                .filter(t -> t.isOngoing())
                .forEach(t -> listString.append(t).append("\n"));
        return listString.toString();
    }

    /**
     * Lists all tasks in tasklist that are upcoming
     *
     * @return statement of all tasks in tasklist that are upcoming, dependent on task typing
     */
    public String listUpcoming() {
        StringBuilder listString = new StringBuilder();
        listString.append("These are your upcoming tasks:\n");
        taskList.stream()
                .filter(t -> t.isUpcoming())
                .forEach(t -> listString.append(t).append("\n"));
        return listString.toString();
    }

    /**
     * Lists all tasks in tasklist that are overdue
     *
     * @return print statement of all tasks in tasklist that are overdue, dependent on task typing
     */
    public String listOverdue() {
        StringBuilder listString = new StringBuilder();
        listString.append("These are your overdue tasks:\n");
        taskList.stream()
                .filter(t -> t.isOverdue())
                .forEach(t -> listString.append(t).append("\n"));
        return listString.toString();
    }

    /**
     * Marks events in tasklist
     * Updates task as done
     *
     * @return statement of task marked
     */
    public String mark(int idx) {
        if (idx > taskList.size()) {
            return "Task out of range, you don't have that many yet... try smaller haha" + "\n";
        }
        Task taskToMark = taskList.get(idx);
        taskToMark.markAsDone();
        assert taskToMark.isDone : "Task is not marked";
        return "Solid work! I've marked this task as done:\n" + taskToMark.toString() + "\n";
    }

    /**
     * Unmarks events in tasklist
     * Updates task as not done
     *
     * @return statement of task unmarked
     */
    public String unmark(int idx) {
        if (idx > taskList.size()) {
            return "Task out of range, you don't have that many yet... try smaller haha" + "\n";
        }
        Task taskToUnmark = taskList.get(idx);
        taskToUnmark.markAsUndone();
        assert !taskToUnmark.isDone : "Task is not unmarked";
        return "Aite bet, I've marked this task as not done yet:\n" + taskToUnmark.toString() + "\n";
    }

    /**
     * Prints task statements from the tasklist that match the input string
     *
     * @return statement of tasks that contain the matching string in its description
     */
    public String find(String match) {
        StringBuilder listString = new StringBuilder();
        listString.append("Here are your matching tasks in your list:\n");
        taskList.stream()
                .filter(t -> t.getDescription()
                        .contains(match))
                .forEach(t -> listString.append(t).append("\n"));
        return listString.toString();
    }

    /**
     * Deletes events in tasklist
     *
     * @return statement of deleted task
     */
    public String delete(int idx) {
        if (idx > taskList.size()) {
            return "Task out of range, you don't have that many yet... try smaller haha" + "\n";
        }
        Task removal = taskList.get(idx);
        taskList.remove(removal);
        return "Sure thing, I've deleted this task:\n" + removal.toString() + "\n";
    }

    /**
     * Adds events into tasklist
     *
     * @return statement of added task
     */
    public String addTask(Task task) {
        try {
            taskList.add(task);
            return "Gotcha. I've added this task:\n" + task.toString() + "\n"
                            + "Now you've got " + taskList.size() + " tasks in the list." + "\n";
        } catch (Exception e) {
            return "Unable to add task, please try again!";
        }

    }
}
