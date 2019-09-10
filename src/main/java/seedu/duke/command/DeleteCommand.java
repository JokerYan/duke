package seedu.duke.command;

import seedu.duke.Duke;
import seedu.duke.Parser;
import seedu.duke.TaskList;

public class DeleteCommand extends Command {
    private TaskList taskList;
    private int index;

    public DeleteCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
    }

    @Override
    public boolean execute() {
        try {
            String msg = taskList.delete(index);
            if (!silent) Duke.getUI().showResponse(msg);
            return true;
        } catch (Parser.UserInputException e) {
            if (!silent) Duke.getUI().showError(e.toString());
            return false;
        }
    }
}
