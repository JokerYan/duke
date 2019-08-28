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

        public String getStatus() {
            if(this.done){
                return "[\u2713] " + this.name;
            }else{
                return "[\u2718] " + this.name;
            }
        }
    }

    public static class ToDo extends Task {
        public ToDo(String name) {
            super(name);
        }
    }

    public static class Deadline extends Task {
        public Deadline(String name) {
            super(name);
        }
    }

    public static class Event extends Task {
        public Event(String name) {
            super(name);
        }
    }
}
