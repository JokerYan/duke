package seedu.duke.command;

import seedu.duke.Duke;
import seedu.duke.Parser;
import seedu.duke.TaskList;

/**
 * Delete Command that is used delete a task from the task list
 * with its index.
 */
public class DeleteCommand extends Command {
    private TaskList taskList;
    private int index;

    /**
     * Instantiation of delete command with all the necessary variables.
     *
     * @param taskList the task list where the task is added to.
     * @param index    the index of task that is to be deleted
     */
    public DeleteCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
    }

    /**
     * Execute the delete command by calling the delete function of task
     * list.
     *
     * @return a flag whether deletion is done successfully. Returns false
     * if the delete function of task list throws an exception.
     */
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
