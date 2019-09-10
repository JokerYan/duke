package seedu.duke.task;

import java.util.Date;

//------------Deadline-------------
public class Deadline extends Task {
    private Date time;

    public Deadline(String name, Date time) {
        super(name);
        this.time = time;
        this.taskType = TaskType.Deadline;
    }

    @Override
    public String toString() {
        return "[D]" + this.getStatus() + " (by: " + formatDate() + ")";
    }

    @Override
    public String toFileString() {
        return (this.isDone ? "1" : "0") + " deadline " + this.name + " /by "
                + formatDate();
    }

    protected String formatDate() {
        return format.format(this.time);
    }
}
