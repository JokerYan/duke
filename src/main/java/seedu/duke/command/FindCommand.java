package seedu.duke.command;

import seedu.duke.Duke;
import seedu.duke.TaskList;

public class FindCommand extends Command {
    private TaskList taskList;
    private String keyword;

    public FindCommand(TaskList taskList, String keyword) {
        this.taskList = taskList;
        this.keyword = keyword;
    }

    @Override
    public boolean execute() {
        String msg = this.taskList.findKeyword(keyword);
        if (!silent) Duke.getUI().showResponse(msg);
        return true;
    }
}
