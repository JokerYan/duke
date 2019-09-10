package seedu.duke.command;

import seedu.duke.Duke;
import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.TaskList;
import seedu.duke.task.ToDo;

import java.util.Date;

public class AddCommand extends Command {
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
                task = new ToDo(name);
                break;
            case Deadline:
                task = new Deadline(name, time);
                break;
            case Event:
                task = new Event(name, time);
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
