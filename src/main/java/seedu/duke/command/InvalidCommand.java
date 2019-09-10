package seedu.duke.command;

public class InvalidCommand extends Command {
    @Override
    public boolean execute() {
//            if (!silent) Duke.getUI().showError("Invalid Command Received");
        return false;
    }
}
