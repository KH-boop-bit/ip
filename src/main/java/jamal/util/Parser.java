package jamal.util;

import jamal.exception.InvalidCommandException;
import jamal.task.ToDo;
import jamal.task.Deadline;
import jamal.task.Event;

import jamal.command.Command;
import jamal.command.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public static Command parse(String input) throws InvalidCommandException {

        Pattern markRegexPattern = Pattern.compile("^/mark\\s\\d{1,3}$"); //^ start, $end, \\d digits, \\s space, {} range
        Pattern unmarkRegexPattern = Pattern.compile("^/unmark\\s\\d{1,3}$");
        Pattern dateTimePattern = Pattern.compile("^\\d{4}-\\d{1,2}-\\d{1,2}T\\d{2}:\\d{2}:\\d{2}$");
        Pattern eventInfoRegexPattern = Pattern.compile("^(.+?)/from(.+?)/to(.+?)$"); //. any char, +? one or more times, () capturing group
        Pattern deleteRegexPattern = Pattern.compile("/delete\\s\\d{1,3}$");

        /// List
        if (input.toLowerCase().startsWith("/list")) {
            String[] listSeq = input.split(" ");
            if (input.toLowerCase().equals(("/list"))) {
                return new ListCommand();
            }
            if (listSeq.length == 2) {
                switch (listSeq[1].toLowerCase()) {
                    case "ongoing":
                        return new ListOngoingCommand();
                    case "upcoming":
                        return new ListUpcomingCommand();
                    case "overdue":
                        return new ListOverdueCommand();
                }
            }
            throw new InvalidCommandException();
        }

        /// Exit
        if (input.toLowerCase().equals("/exit")) {
            return new ExitCommand();
        }
        /// Mark
        if (markRegexPattern.matcher(input.toLowerCase()).matches()) { //regex must match specific mark int, and allow mark int extra to bypass i.e mark 20 exam papers
            int taskNumber = Integer.parseInt(input.split(" ")[1]); //split string to mark and num, parse stringnum to int
            return new MarkCommand(taskNumber - 1);
        }

        /// Unmark
        if (unmarkRegexPattern.matcher(input.toLowerCase()).matches()) {
            int taskNumber = Integer.parseInt(input.split(" ")[1]);
            return new UnmarkCommand(taskNumber - 1);
        }

        /// Delete
        if (deleteRegexPattern.matcher(input.toLowerCase()).matches()) {
            int taskNumber = Integer.parseInt(input.split(" ")[1]);
            return new DeleteCommand(taskNumber - 1);
        }

        /// Todo task
        if (input.toLowerCase().startsWith("/todo")) {
            String toDoDescription = input.split("\\s+", 2)[1]; //split two parts, todo and rem string
            ToDo toDoTask = new ToDo(toDoDescription);
            return new ToDoTaskCommand(toDoTask);
        }

        /// jamal.task.Deadline task
        if (input.toLowerCase().startsWith("/deadline")) {
            String secondHalfString = input.split("\\s+", 2)[1];
            String[] deadlineInfo = secondHalfString.split("/by"); //single split since info desc is split by /by
            Matcher dateTimeInfo = dateTimePattern.matcher(deadlineInfo[1].trim());
            if (dateTimeInfo.matches()) { //make sure date formatting is correct
                Deadline deadlineTask = new Deadline(deadlineInfo[0].trim(), deadlineInfo[1].trim());
                return new DeadlineTaskCommand(deadlineTask);
            }
        }

        /// jamal.task.Event task
        if (input.toLowerCase().startsWith("/event")) {
            String secondHalfString = input.split("\\s+", 2)[1];
            Matcher eventInfo = eventInfoRegexPattern.matcher(secondHalfString); //refer to eventInfoRegexPattern at top of main, need to split into 3 groups for 3 params
            if (eventInfo.matches()) { //make sure date formats and groupings are correct
                Event eventTask = new Event(eventInfo.group(1).trim(), eventInfo.group(2).trim(), eventInfo.group(3).trim()); //group 0 is everything
                return new EventTaskCommand(eventTask);
            }
        }

    throw new InvalidCommandException();
    }
}
