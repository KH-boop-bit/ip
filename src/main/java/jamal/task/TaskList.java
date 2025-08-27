package jamal.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    protected ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

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

    public void list() {
        System.out.println("Here are your tasks:\n");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(
                    (i + 1) + ". "
                            + taskList.get(i).toString() + "\n");
        }
    }

    public void listOngoing() {
        System.out.println("These are your ongoing tasks:\n");
        taskList.stream()
                .filter(t -> t.isOngoing())
                .forEach(l -> System.out.println(l.toString() + "\n"));
    }

    public void listUpcoming() {
        System.out.println("These are your upcoming tasks:\n");
        taskList.stream()
                .filter(t -> t.isUpcoming())
                .forEach(l -> System.out.println(l.toString() + "\n"));
    }

    public void listOverdue() {
        System.out.println("These are your overdued tasks:\n");
        taskList.stream()
                .filter(t -> t.isOverdue())
                .forEach(l -> System.out.println(l.toString() + "\n"));
    }

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
