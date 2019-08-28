package seedu.duke;

public class Activities {
    public static class Task {
        protected String name;
        protected boolean done;
        public Task(String name) {
            this.name = name;
            this.done = false;
        }

        public void markDone() {
            this.done = true;
        }

        public boolean getDone() {
            return this.done;
        }

        protected String getStatus() {
            if(this.done){
                return "[\u2713] " + this.name;
            }else{
                return "[\u2718] " + this.name;
            }
        }

        public String toString() {
            return this.getStatus();
        }

        public String toFileString() {
            return this.toString();
        }
    }

    //------------ToDo-------------
    public static class ToDo extends Task {
        public ToDo(String name) {
            super(name);
        }

        @Override
        public String toString() {
            return "[T]" + this.getStatus();
        }

        @Override
        public String toFileString() {
            return (this.done ? "1" : "0") + " todo " + this.name;
        }
    }

    //------------Deadline-------------
    public static class Deadline extends Task {
        private String time;

        public Deadline(String name, String time) {
            super(name);
            this.time = time;
        }

        @Override
        public String toString() {
            return "[D]" + this.getStatus() + " (by: " + this.time + ")";
        }

        @Override
        public String toFileString() {
            return (this.done ? "1" : "0") + " deadline " + this.name + " /by "
                    + this.time;
        }
    }

    //------------Event-------------
    public static class Event extends Task {
        private String time;
        public Event(String name, String time) {
            super(name);
            this.time = time;
        }

        @Override
        public String toString() {
            return "[E]" + this.getStatus() + " (at: " + this.time + ")";
        }

        @Override
        public String toFileString() {
            return (this.done ? "1" : "0") + " event " + this.name + " /at "
                    + this.time;
        }
    }
}
