package seedu.duke.task;

import java.util.Date;

//------------Event-------------
public class Event extends Task {
    private Date time;

    public Event(String name, Date time) {
        super(name);
        this.time = time;
        this.taskType = TaskType.Event;
    }

    @Override
    public String toString() {
        return "[E]" + this.getStatus() + " (at: " + formatDate() + ")";
    }

    @Override
    public String toFileString() {
        return (this.isDone ? "1" : "0") + " event " + this.name + " /at "
                + formatDate();
    }

    protected String formatDate() {
        return format.format(this.time);
    }
}
