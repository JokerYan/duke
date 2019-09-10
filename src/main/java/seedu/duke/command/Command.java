package seedu.duke.command;

public abstract class Command {
    protected boolean silent = false;

    public abstract boolean execute();

    public void setSilent() {
        this.silent = true;
    }
}
