package seedu.duke;

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
        if(this.isDone){
            return "[\u2713] " + this.name;
        }else{
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

    //------------ToDo-------------
    public static class ToDo extends Task {
        public ToDo(String name) {
            super(name);
            this.taskType = TaskType.ToDo;
        }

        @Override
        public String toString() {
            return "[T]" + this.getStatus();
        }

        @Override
        public String toFileString() {
            return (this.isDone ? "1" : "0") + " todo " + this.name;
        }
    }

    //------------Deadline-------------
    public static class Deadline extends Task {
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

    //------------Event-------------
    public static class Event extends Task {
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
}
