package seedu.duke.command;

import seedu.duke.Duke;
import seedu.duke.TaskList;

public class ListCommand extends Command {
    private TaskList taskList;

    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public boolean execute() {
        if (!silent) Duke.getUI().showResponse(this.taskList.toString());
        return true;
    }
}
