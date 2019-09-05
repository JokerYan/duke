package seedu.duke;

import java.util.Date;

public abstract class Command {
    protected boolean silent = false;

    public abstract boolean execute();

    public void setSilent() {
        this.silent = true;
    }

    public static class InvalidCommand extends Command {
        @Override
        public boolean execute() {
//            if (!silent) Duke.getUI().showError("Invalid Command Received");
            return false;
        }
    }

    public static class ExitCommand extends Command {
        @Override
        public boolean execute() {
            return false;
        }
    }

    ;

    public static class DoneCommand extends Command {
        private int index;

        public DoneCommand(int i) {
            index = i;
        }

        @Override
        public boolean execute() {
            try {
                String msg = Duke.getTaskList().markDone(index);
                if (!silent) Duke.getUI().showResponse(msg);
                return true;
            } catch (Parser.UserInputException e) {
                if (!silent) Duke.getUI().showError(e.toString());
                return false;
            }
        }
    }

    public static class ListCommand extends Command {
        private TaskList taskList;

        public ListCommand(TaskList taskList) {
            this.taskList = taskList;
        }

        @Override
        public boolean execute() {
            if (!silent) Duke.getUI().showResponse(this.taskList.toString());
            return true;
        }
    }

    public static class DeleteCommand extends Command {
        private TaskList taskList;
        private int index;

        public DeleteCommand(TaskList taskList, int index) {
            this.taskList = taskList;
            this.index = index;
        }

        @Override
        public boolean execute() {
            try {
                String msg = taskList.delete(index);
                if (!silent) Duke.getUI().showResponse(msg);
                return true;
            } catch (Parser.UserInputException e) {
                if (!silent) Duke.getUI().showError(e.toString());
                return false;
            }
        }
    }

    public static class FindCommand extends Command {
        private TaskList taskList;
        private String keyword;

        public FindCommand(TaskList taskList, String keyword) {
            this.taskList = taskList;
            this.keyword = keyword;
        }

        @Override
        public boolean execute() {
            String msg = this.taskList.findKeyword(keyword);
            if (!silent) Duke.getUI().showResponse(msg);
            return true;
        }
    }

    public static class AddCommand extends Command {
        private TaskList taskList;
        private Task.TaskType taskType;
        private String name;
        private Date time;

        public AddCommand(TaskList taskList, Task.TaskType taskType, String name, Date time) {
            this.taskList = taskList;
            this.taskType = taskType;
            this.name = name;
            this.time = time;
        }

        @Override
        public boolean execute() {
            Task task;
            switch (taskType) {
                case ToDo:
                    task = new Task.ToDo(name);
                    break;
                case Deadline:
                    task = new Task.Deadline(name, time);
                    break;
                case Event:
                    task = new Task.Event(name, time);
                    break;
                default:
                    return false;
            }
            taskList.add(task);
            if (!silent) {
                String msg = "Got it. I've added this task: \n";
                msg += "  " + task.toString() + "\n";
                msg += "Now you have " + Integer.toString(taskList.size()) + " task(s) in the list. ";
                Duke.getUI().showResponse(msg);
            }
            return true;
        }
    }
}
