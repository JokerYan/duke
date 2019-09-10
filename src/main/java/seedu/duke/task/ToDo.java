package seedu.duke.task;

//------------ToDo-------------
public class ToDo extends Task {
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
