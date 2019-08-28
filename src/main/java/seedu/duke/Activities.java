package seedu.duke;

public class Activities {
    public static class Task {
        private String name;
        private boolean done;
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
    }
}
