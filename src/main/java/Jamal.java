import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner; //import scanner class for inputs
import java.util.regex.*; //import regex for matching words

public class Jamal {

    public static class Task {
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

        @Override
        public String toString() {
            return "[" + this.getStatus() + "] " + description;
        }
    }

    public static class ToDo extends Task {

        public ToDo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    public static class Deadline extends Task {

        protected String by;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }

    public static class Event extends Task {

        protected String start;
        protected String end;

        public Event(String description, String start, String end) {
            super(description);
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
        }
    }

    public static void main(String[] args) {
        String lineBreak = "____________________________________________________________\n";
        String lineEnd = "____________________________________________________________";
        Pattern markRegexPattern = Pattern.compile("^mark\\s\\d{1,3}$"); //^ start, $end, \\d digits, \\s space, {} range
        Pattern unmarkRegexPattern = Pattern.compile("^unmark\\s\\d{1,3}$");
        Pattern eventInfoRegexPattern = Pattern.compile("^(.+?)/from(.+?)/to(.+?)$"); //. any char, +? one or more times, () capturing group
        Pattern deleteRegexPattern = Pattern.compile("delete\\s\\d{1,3}$");

        String welcome =
            lineBreak
            + "Yo! I'm Jamal\n"
            + "What can I do for ya?\n"
            + lineEnd;

        String exit =
            lineBreak
            + "Catch you later!\n"
            + lineEnd;

        System.out.println(welcome);
/// start scanning input
        Scanner scanner = new Scanner(System.in);

        ArrayList<Task> taskList = new ArrayList<>();

        while (true) { //keep the while loop active until exit string is executed
            System.out.println(""); //empty string placeholder for input
            String input = scanner.nextLine();
/// exit
            if (input.toLowerCase().equals("bye")) { //ignorecase so all Bye bye works
                break;
            }
/// list
            if (input.toLowerCase().equals("list")) {
                System.out.println(
                        lineBreak + "Here are your tasks:\n");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println(
                        (i + 1) + ". "
                        + taskList.get(i).toString() + "\n");
                }
                System.out.println(lineEnd);
                continue;
            }
/// mark and unmark
            if (markRegexPattern.matcher(input.toLowerCase()).matches()) { //regex must match specific mark int, and allow mark int extra to bypass i.e mark 20 exam papers
                int taskNumber = Integer.parseInt(input.split(" ")[1]); //split string to mark and num, parse stringnum to int
                if (taskNumber > taskList.size()) {
                    System.out.println(lineBreak + "Task out of range" + "\n"+ lineEnd);
                } else {
                    taskList.get(taskNumber - 1).markAsDone();
                    System.out.println(
                        lineBreak
                        + "Solid work! I've marked this task as done:\n"
                        + taskList.get(taskNumber - 1).toString() + "\n"
                        + lineEnd
                    );
                }
                continue;
            }
            if (unmarkRegexPattern.matcher(input.toLowerCase()).matches()) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]);
                if (taskNumber > taskList.size()) {
                    System.out.println(lineBreak + "Task out of range, you don't have that many yet... try smaller haha" + "\n" + lineEnd);
                } else {
                    taskList.get(taskNumber - 1).markAsUndone();
                    System.out.println(
                        lineBreak
                        + "Aite bet, I've marked this task as not done yet:\n"
                        + taskList.get(taskNumber - 1).toString() + "\n"
                        + lineEnd
                    );
                }
                continue;
            }

///delete
            if (deleteRegexPattern.matcher(input.toLowerCase()).matches()) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]);
                if (taskNumber > taskList.size()) {
                    System.out.println(lineBreak + "Task out of range, you don't have that many yet... try smaller haha" + "\n" + lineEnd);
                } else {
                    Task removal = taskList.get(taskNumber - 1);
                    taskList.remove(removal);
                    System.out.println(
                        lineBreak
                        + "Sure thing, I've deleted this task:\n"
                        + removal.toString() + "\n"
                        + lineEnd
                    );
                }
                continue;
            }

///todo task
            if (input.toLowerCase().startsWith("todo")) {
                try {
                    String toDoDescription = input.split("\\s+", 2)[1]; //split two parts, todo and rem string
                    ToDo toDoTask = new ToDo(toDoDescription);
                    taskList.add(toDoTask);
                    System.out.println(
                        lineBreak
                        + "Gotcha. I've added this task:\n"
                        + toDoTask.toString() + "\n"
                        + "Now you've got " + taskList.size() + " tasks in the list." + "\n"
                        + lineEnd
                    );
                } catch (Exception e) {
                    System.out.println(lineBreak + "Invalid todo format, use: todo {description}\n" + lineEnd);
                }
                continue;
            }
///deadline task
            if (input.toLowerCase().startsWith("deadline")) {
                String secondHalfString = input.split("\\s+", 2)[1];
                try {
                    String[] deadlineInfo = secondHalfString.split("/by"); //single split since info desc is split by /by
                    Deadline deadlineTask = new Deadline(deadlineInfo[0].trim(), deadlineInfo[1].trim());
                    taskList.add(deadlineTask);
                    System.out.println(
                        lineBreak
                        + "Gotcha. I've added this task:\n"
                        + deadlineTask.toString() + "\n"
                        + "Now you've got " + taskList.size() + " tasks in the list." + "\n"
                        + lineEnd
                    );
                } catch (Exception e) {
                    System.out.println(lineBreak + "Invalid deadline format, use: {description} /by {time}\n" + lineEnd);
                }

                continue;
            }
///event task
            if (input.toLowerCase().startsWith("event")) {
                String secondHalfString = input.split("\\s+", 2)[1];
                Matcher eventInfo = eventInfoRegexPattern.matcher(secondHalfString); //refer to eventInfoRegexPattern at top of main, need to split into 3 groups for 3 params
                if (eventInfo.matches()) {
                    Event eventTask = new Event(eventInfo.group(1).trim(), eventInfo.group(2).trim(), eventInfo.group(3).trim()); //group 0 is everything
                    taskList.add(eventTask);
                    System.out.println(
                        lineBreak
                        + "Gotcha. I've added this task:\n"
                        + eventTask.toString() + "\n"
                        + "Now you've got " + taskList.size() + " tasks in the list." + "\n"
                        + lineEnd
                    );
                } else {
                    System.out.println(lineBreak + "Invalid event format, use: {description} /from {time} /to {time}\n" + lineEnd);
                }
                continue;
            }
///invalid prompt
            System.out.println(
                lineBreak
                + "Buddy I have no clue what you are on about...\n"
                + lineEnd);
        }
        scanner.close(); //release system resources
        System.out.println(exit);
    }
}
