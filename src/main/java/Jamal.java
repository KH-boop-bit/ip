import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner; //import scanner class for inputs
import java.util.regex.*; //import regex for matching words

public class Jamal {

    public static void main(String[] args) {
        String lineBreak = "____________________________________________________________\n";
        String lineEnd = "____________________________________________________________";
        Pattern markRegexPattern = Pattern.compile("^/mark\\s\\d{1,3}$"); //^ start, $end, \\d digits, \\s space, {} range
        Pattern unmarkRegexPattern = Pattern.compile("^/unmark\\s\\d{1,3}$");
        Pattern eventInfoRegexPattern = Pattern.compile("^(.+?)/from(.+?)/to(.+?)$"); //. any char, +? one or more times, () capturing group
        Pattern deleteRegexPattern = Pattern.compile("/delete\\s\\d{1,3}$");

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

/// check for datafile, create if does not exist
        String filePath = "data/Jamal.txt";
        File dataFile = new File(filePath);
        if (!dataFile.exists()) { //does not yet exist, then create
            dataFile.getParentFile().mkdir(); //make the data folder if does not yet exist
            try {
                if (dataFile.createNewFile()) { //boolean, attempt to create file
                    System.out.print("Data file Created");
                } else {
                    System.out.print("Unable to create data file");
                    return;
                }
            } catch (IOException e) {
                System.out.print("Unable to create data file");
                return;
            }
        }

/// start scanning input
        Scanner inputScanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

/// copy local data into taskList
        try {
            Scanner initScanner = new Scanner(dataFile);
            while (initScanner.hasNext()) {
                String[] temp = initScanner.nextLine().split("`"); //` as an uncommon separator
                String type = temp[0];
                if (type.equals("T")) {
                    taskList.add(new ToDo(temp[2]));
                } else if (type.equals("D")) {
                    taskList.add(new Deadline(temp[2], temp[3]));
                } else { //event "E"
                    taskList.add(new Event(temp[2], temp[3], temp[4]));
                }
            }
            initScanner.close();
        } catch (FileNotFoundException e) {
            System.out.print("Unable to find file");
            return;
        }

        while (true) { //keep the while loop active until exit string is executed
            System.out.println(""); //empty string placeholder for input
            String input = inputScanner.nextLine();
/// exit
            if (input.toLowerCase().equals("/exit")) {
                break;
            }
/// list
            if (input.toLowerCase().startsWith("/list")) {
                String[] listSeq = input.split(" ");
                if (listSeq.length == 1 && listSeq[0].toLowerCase().equals(("/list"))) {
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
                if (listSeq.length == 2) {
                    if (listSeq[1].toLowerCase().equals("ongoing")) {
                        System.out.println(lineBreak + "These are your ongoing tasks:\n");
                        taskList.stream()
                                .filter(t -> t.isOngoing())
                                .forEach(l -> System.out.println(l.toString() + "\n"));
                        System.out.println(lineEnd);
                        continue;
                    }
                    if (listSeq[1].toLowerCase().equals("upcoming")) {
                        System.out.println(lineBreak + "These are your upcoming tasks:\n");
                        taskList.stream()
                                .filter(t -> t.isUpcoming())
                                .forEach(l -> System.out.println(l.toString() + "\n"));
                        System.out.println(lineEnd);
                        continue;
                    }
                    if (listSeq[1].toLowerCase().equals("overdue")) {
                        System.out.println(lineBreak + "These are your overdued tasks:\n");
                        taskList.stream()
                                .filter(t -> t.isOverdue())
                                .forEach(l -> System.out.println(l.toString() + "\n"));
                        System.out.println(lineEnd);
                        continue;
                    }
                }
                System.out.println("Invalid list format, try: /list {ongoing/upcoming/overdue}");
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
                    FileWrite.markLine(filePath, true,taskNumber - 1);
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
                    FileWrite.markLine(filePath, false,taskNumber - 1);
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
                    FileWrite.deleteLine(filePath, taskNumber - 1);
                }
                continue;
            }

///todo task
            if (input.toLowerCase().startsWith("/todo")) {
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
                    FileWrite.addLine(filePath, "T`UM`" + toDoDescription);
                } catch (Exception e) {
                    System.out.println(lineBreak + "Invalid todo format, use: todo {description}\n" + lineEnd);
                }
                continue;
            }
///deadline task
            if (input.toLowerCase().startsWith("/deadline")) {
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
                    FileWrite.addLine(filePath, "D`UM`" + deadlineInfo[0].trim() + "`" + deadlineInfo[1].trim());
                } catch (Exception e) {
                    System.out.println(lineBreak + "Invalid deadline format, use: {description} /by {yyyy-dd-mmThh:mm:ss}\n" + lineEnd);
                }

                continue;
            }
///event task
            if (input.toLowerCase().startsWith("/event")) {
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
                    FileWrite.addLine(filePath, "E`UM`" + eventInfo.group(1).trim() + "`" + eventInfo.group(2).trim() + "`" + eventInfo.group(3).trim());
                } else {
                    System.out.println(lineBreak + "Invalid event format, use: {description} /from {yyyy-dd-mmThh:mm:ss} /to {yyyy-dd-mmThh:mm:ss}\n" + lineEnd);
                }
                continue;
            }
///invalid prompt
            System.out.println(
                lineBreak
                + "Buddy I have no clue what you are on about...\n"
                + lineEnd);
        }
        inputScanner.close(); //release system resources
        System.out.println(exit);
    }
}
