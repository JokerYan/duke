package seedu.duke;

import java.text.ParseException;
import java.util.Date;

public class Parser {
    public static Command parseCommand(String input) {
        UI ui = Duke.getUI();
        TaskList taskList = Duke.getTaskList();

        if (input.equals("bye")) {
            return new Command.ExitCommand();
        } else if (input.equals("list")) {
            return new Command.ListCommand(taskList);
        } else if (input.startsWith("done ")) {
            if (input.length() <= 5) {
                ui.showError("Please enter index of task after \'done\'");
                return new Command.InvalidCommand();
            } else {
                try {
                    int index = parseIndex(input);
                    return new Command.DoneCommand(index);
                } catch (NumberFormatException e) {
                    ui.showError(e.toString());
                } catch (UserInputException e) {
                    ui.showError("Please enter correct task index");
                }
            }
        } else if (input.startsWith("delete")) {
            if (input.length() <= 7) {
                ui.showError("Please enter index of task after \'delete\'");
                return new Command.InvalidCommand();
            } else {
                try {
                    int index = parseIndex(input);
                    return new Command.DeleteCommand(taskList, index);
                } catch (NumberFormatException e) {
                    ui.showError(e.toString());
                } catch (UserInputException e) {
                    ui.showError("Please enter correct task index");
                }
            }
        } else if (input.startsWith("find ")) {
            if (input.length() <= 5) {
                ui.showError("Please enter keyword for searching after \'find\'");
            } else {
                String keyword = input.split(" ", 2)[1];
                return new Command.FindCommand(taskList, keyword);
            }
        } else {
            try {
                return parseTask(taskList, input);
            } catch (UserInputException e) {
                ui.showError(e.toString());
            }
        }
        return new Command.InvalidCommand();
    }

    private static int parseIndex(String input) throws NumberFormatException, UserInputException {
        String[] splited = input.split(" ", 2);
        if (splited.length < 2) {
            throw new UserInputException("Please enter task index");
        }
        return Integer.parseInt(splited[1]) - 1;
    }

    public static Command parseTask(TaskList taskList, String input) throws UserInputException {
        Task.TaskType taskType;
        String name;
        Date time = new Date();

        if (input.startsWith("todo")) {
            taskType = Task.TaskType.ToDo;
            if (input.length() <= 5) {
                throw new Parser.UserInputException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            name = input.substring(5);
        } else if (input.startsWith("deadline")) {
            taskType = Task.TaskType.Deadline;
            if (input.length() <= 9) {
                throw new Parser.UserInputException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            input = input.substring(9);
            if (!input.contains(" /by ")) {
                throw new Parser.UserInputException("☹ OOPS!!! A deadline must have a time specified.");
            }
            name = input.split(" /by ", 2)[0];
            String timeString = input.split(" /by ", 2)[1];
            try {
                time = Task.parseDate(timeString);
            } catch (ParseException e) {
                throw new Parser.UserInputException("Wrong date/time format. Write date/time in dd/MM/yyyy HHmm format.");
            }
        } else if (input.startsWith("event")) {
            taskType = Task.TaskType.Event;
            if (input.length() <= 6) {
                throw new Parser.UserInputException("☹ OOPS!!! The description of a event cannot be empty.");
            }
            input = input.substring(6);
            if (!input.contains(" /at ")) {
                throw new Parser.UserInputException("☹ OOPS!!! A event must have a time specified.");
            }
            name = input.split(" /at ", 2)[0];
            String timeString = input.split(" /at ", 2)[1];
            try {
                time = Task.parseDate(timeString);
            } catch (ParseException e) {
                throw new Parser.UserInputException("Wrong date/time format. Write date/time in dd/MM/yyyy HHmm format.");
            }
        } else {
            throw new Parser.UserInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return new Command.AddCommand(taskList, taskType, name, time);
    }

    public static class UserInputException extends Exception {
        private String msg;

        public UserInputException(String msg) {
            super();
            this.msg = msg;
        }

        public String toString() {
            return msg;
        }
    }
}
