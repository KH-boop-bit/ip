package jamal.task;

import java.time.format.DateTimeParseException;
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
            }
        }

    }

    /**
     * Lists all tasks in tasklist
     *
     * @returns print statement of all tasks in tasklist
     */
    public void list() {
        System.out.println("Here are your tasks:\n");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(
                    (i + 1) + ". "
                            + taskList.get(i).toString() + "\n");
        }
    }

    /**
     * Lists all tasks in tasklist that are ongoing
     *
     * @returns print statement of all tasks in tasklist that are ongoing, dependent on task typing
     */
    public void listOngoing() {
        System.out.println("These are your ongoing tasks:\n");
        taskList.stream()
                .filter(t -> t.isOngoing())
                .forEach(l -> System.out.println(l.toString() + "\n"));
    }

    /**
     * Lists all tasks in tasklist that are upcoming
     *
     * @returns print statement of all tasks in tasklist that are upcoming, dependent on task typing
     */
    public void listUpcoming() {
        System.out.println("These are your upcoming tasks:\n");
        taskList.stream()
                .filter(t -> t.isUpcoming())
                .forEach(l -> System.out.println(l.toString() + "\n"));
    }

    /**
     * Lists all tasks in tasklist that are overdue
     *
     * @returns print statement of all tasks in tasklist that are overdue, dependent on task typing
     */
    public void listOverdue() {
        System.out.println("These are your overdued tasks:\n");
        taskList.stream()
                .filter(t -> t.isOverdue())
                .forEach(l -> System.out.println(l.toString() + "\n"));
    }

    /**
     * Marks events in tasklist
     * Updates task as done
     *
     * @return print statement of task marked
     */
    public void mark(int idx) {
        if (idx > taskList.size()) {
            System.out.println("Task out of range, you don't have that many yet... try smaller haha" + "\n");
        } else {
            taskList.get(idx).markAsDone();
            System.out.println(
                    "Solid work! I've marked this task as done:\n"
                            + taskList.get(idx).toString() + "\n");
        }
    }

    /**
     * Unmarks events in tasklist
     * Updates task as not done
     *
     * @return print statement of task unmarked
     */
    public void unmark(int idx) {
        if (idx > taskList.size()) {
            System.out.println("Task out of range, you don't have that many yet... try smaller haha" + "\n");
        } else {
            taskList.get(idx).markAsUndone();
            System.out.println(
                    "Aite bet, I've marked this task as not done yet:\n"
                            + taskList.get(idx).toString() + "\n");
        }
    }

    /**
     * Deletes events in tasklist
     *
     * @return print statement of deleted task
     */
    public void delete(int idx) {
        if (idx > taskList.size()) {
            System.out.println("Task out of range, you don't have that many yet... try smaller haha" + "\n");
        } else {
            Task removal = taskList.get(idx);
            taskList.remove(removal);
            System.out.println(
                    "Sure thing, I've deleted this task:\n"
                            + removal.toString() + "\n");
        }
    }

    /**
     * Adds events into tasklist
     *
     * @return print statement of added task
     */
    public void addTask(Task task) {
        try {
            taskList.add(task);
            System.out.println(
                    "Gotcha. I've added this task:\n"
                            + task.toString() + "\n"
                            + "Now you've got " + taskList.size() + " tasks in the list." + "\n");
        } catch (Exception e) {
            System.out.println("Invalid format");
        }

    }
}
