package seedu.duke.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
    protected String name;
    protected boolean isDone;
    protected TaskType taskType;
    protected static SimpleDateFormat format =
            new SimpleDateFormat("dd/MM/yyyy HHmm");

    public enum TaskType {
        ToDo, Deadline, Event
    }

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public boolean getDone() {
        return this.isDone;
    }

    protected String getStatus() {
        if (this.isDone) {
            return "[\u2713] " + this.name;
        } else {
            return "[\u2718] " + this.name;
        }
    }

    public TaskType getTaskType() {
        return this.taskType;
    }

    public String toString() {
        return this.getStatus();
    }

    public String toFileString() {
        return this.toString();
    }

    public static Date parseDate(String dateString) throws ParseException {
//            System.out.println(dateString);
        return format.parse(dateString);
    }

    public boolean matchKeyword(String keyword) {
        return this.toString().contains(keyword);
    }

}
