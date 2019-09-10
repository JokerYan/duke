package seedu.duke.command;

import seedu.duke.Duke;
import seedu.duke.Parser;

public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int i) {
        index = i;
    }

    @Override
    public boolean execute() {
        try {
            String msg = Duke.getTaskList().markDone(index);
            if (!silent) Duke.getUI().showResponse(msg);
            return true;
        } catch (Parser.UserInputException e) {
            if (!silent) Duke.getUI().showError(e.toString());
            return false;
        }
    }
}
