package seedu.duke;

import seedu.duke.command.AddCommand;
import seedu.duke.command.Command;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.DoneCommand;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.FindCommand;
import seedu.duke.command.InvalidCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.task.Task;

import java.text.ParseException;
import java.util.Date;

/**
 * A class that contains helper functions used to process user inputs. It also contains UserInputException
 * that is used across the project to handle the unexpected user input.
 */
public class Parser {
    /**
     * Parse the user/file input as command. It returns a command that is not yet executed. It also needs to
     * get a UI from Duke to display the messages.
     *
     * @param input the user/file input that is to be parsed to a command
     * @return the parse result, which is a command ready to be executed
     */
    public static Command parseCommand(String input) {
        UI ui = Duke.getUI();
        TaskList taskList = Duke.getTaskList();

        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand(taskList);
        } else if (input.startsWith("done ")) {
            if (input.length() <= 5) {
                ui.showError("Please enter index of task after \'done\'");
                return new InvalidCommand();
            } else {
                try {
                    int index = parseIndex(input);
                    return new DoneCommand(index);
                } catch (NumberFormatException e) {
                    ui.showError(e.toString());
                } catch (UserInputException e) {
                    ui.showError("Please enter correct task index");
                }
            }
        } else if (input.startsWith("delete")) {
            if (input.length() <= 7) {
                ui.showError("Please enter index of task after \'delete\'");
                return new InvalidCommand();
            } else {
                try {
                    int index = parseIndex(input);
                    return new DeleteCommand(taskList, index);
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
                return new FindCommand(taskList, keyword);
            }
        } else {
            try {
                return parseTask(taskList, input);
            } catch (UserInputException e) {
                ui.showError(e.toString());
            }
        }
        return new InvalidCommand();
    }

    private static int parseIndex(String input) throws NumberFormatException, UserInputException {
        String[] splited = input.split(" ", 2);
        if (splited.length < 2) {
            throw new UserInputException("Please enter task index");
        }
        return Integer.parseInt(splited[1]) - 1;
    }

    /**
     * Parse the specific part of a user/file input that is relevant to a task. A successful parsing always
     * returns an AddCommand, as it is assumed that an input starting with a task name is an add command.
     *
     * @param taskList target task list to which the new task is to be added to
     * @param input    user/file input ready to be parsed
     * @return an AddCommand of the task parsed from the input
     * @throws UserInputException an exception when the parsing is failed, probably due to the wrong format of
     *                            input
     */
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
                throw new Parser.UserInputException("☹ OOPS!!! The description of a deadline cannot be "
                        + "empty.");
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
                throw new Parser.UserInputException("Wrong date/time format. Write date/time in dd/MM/yyyy "
                        + "HHmm format.");
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
                throw new Parser.UserInputException("Wrong date/time format. Write date/time in dd/MM/yyyy "
                        + "HHmm format.");
            }
        } else {
            throw new Parser.UserInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return new AddCommand(taskList, taskType, name, time);
    }

    /**
     * An type of exception dedicated to handling the unexpected user/file input. The message contains more
     * specific information.
     */
    public static class UserInputException extends Exception {
        private String msg;

        /**
         * Instantiate the exception with a message, which is ready to be displayed by the UI.
         *
         * @param msg the message that is ready to be displayed by UI.
         */
        public UserInputException(String msg) {
            super();
            this.msg = msg;
        }

        /**
         * Convert the exception ot string by returning its message, so that it can be displayed by the UI.
         *
         * @return the message of the exception
         */
        public String toString() {
            return msg;
        }
    }
}
