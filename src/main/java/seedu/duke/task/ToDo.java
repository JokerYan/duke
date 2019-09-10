package seedu.duke.task;

/**
 * ToDo class is a rather basic type of Task, which has only name and isDone
 * status, without any date/time involved.
 */
public class ToDo extends Task {
    /**
     * Instantiate the ToDo class, which only takes in the name and set
     * isDone flag to false like all tasks.
     *
     * @param name name of the task
     */
    public ToDo(String name) {
        super(name);
        this.taskType = TaskType.ToDo;
    }

    /**
     * Function that convert the task to a human readable string
     * containing important information about the ToDo, including
     * the type of this task.
     *
     * @return a human readable string containing the important information
     */
    @Override
    public String toString() {
        return "[T]" + this.getStatus();
    }

    /**
     * Functions that output a string with all the information of this ToDo
     * to be stored in a file for future usage.
     *
     * @return a string containing all information of this ToDo
     */
    @Override
    public String toFileString() {
        return (this.isDone ? "1" : "0") + " todo " + this.name;
    }
}
