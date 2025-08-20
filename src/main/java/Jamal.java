import java.util.Locale;
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
    }

    public static void main(String[] args) {
        String lineBreak = "____________________________________________________________\n";
        String lineEnd = "____________________________________________________________";
        Pattern markRegexPattern = Pattern.compile("^mark\\s\\d{1,3}$"); //^ start, $end, \\d digits, \\s space, {} range
        Pattern unmarkRegexPattern = Pattern.compile("^unmark\\s\\d{1,3}$");

        String welcome =
            lineBreak
            + "Hello! I'm Jamal\n"
            + "What can I do for you?\n"
            + lineEnd;

        String exit =
                lineBreak
                + "Bye. Hope to see you again soon!\n"
                + lineEnd;

        System.out.println(welcome);
/// start scanning input
        Scanner scanner = new Scanner(System.in);

        Task[] taskList = new Task[100];
        int taskCounter = 0; //global counter for number of list items

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
                        lineBreak + "Here are the tasks in your list:\n");
                for (int i = 0; i < taskCounter; i++) {
                    System.out.println(
                            (i + 1) + ". ["
                            + taskList[i].getStatus()
                            + "] " + taskList[i].description + "\n");
                }
                System.out.println(lineEnd);
                continue;
            }
/// mark and unmark
            if (markRegexPattern.matcher(input.toLowerCase()).matches()) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]); //split string to mark and num, parse stringnum to int
                if (taskNumber > taskCounter) {
                    System.out.println(lineBreak + "Task out of range" + "\n"+ lineEnd);
                } else {
                    taskList[taskNumber - 1].markAsDone();
                    System.out.println(
                            lineBreak
                            + "Nice! I've marked this task as done:\n"
                            + "[X] " + taskList[taskNumber - 1].description + "\n"
                            + lineEnd
                    );
                }
                continue;
            }
            if (unmarkRegexPattern.matcher(input.toLowerCase()).matches()) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]);
                if (taskNumber > taskCounter) {
                    System.out.println(lineBreak + "Task out of range" + "\n" + lineEnd);
                } else {
                    taskList[taskNumber - 1].markAsUndone();
                    System.out.println(
                            lineBreak
                                    + "OK, I've marked this task as not done yet:\n"
                                    + "[ ] " + taskList[taskNumber - 1].description + "\n"
                                    + lineEnd
                    );
                }
                continue;
            }
/// add tasks
            System.out.println(
                    lineBreak
                    + "added: " + input + "\n"
                    + lineEnd);
            taskList[taskCounter] = new Task(input); //put input into array first
            taskCounter++; //maintain a global counter
        }
        scanner.close(); //release system resources
        System.out.println(exit);
    }
}
