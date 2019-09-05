package seedu.duke;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class TaskList extends ArrayList<Task> {
    @Override
    public String toString() {
        if (this.size() == 0) {
            return "There is nothing in your list.";
        }
        String msg = "Here are the tasks in your list:";
        for (int i = 0; i < this.size(); i++) {
            msg += "\n" + (i + 1);
            msg += ". " + this.get(i);
        }
        return msg;
    }

    public String findKeyword(String keyword) {
        ArrayList<Task> searchResult = new ArrayList<>();
        for (Task task : this) {
            if (task.matchKeyword(keyword)) {
                searchResult.add(task);
            }
        }
        String msg = "";
        if (searchResult.size() > 0) {
            msg += "Here are the matching tasks in your list:";
            for (int i = 0; i < searchResult.size(); i++) {
                msg += "\n" + (i + 1) + ". " + searchResult.get(i);
            }
        } else {
            msg += "There is no matching task in your list.";
        }
        return msg;
    }

    public String markDone(int index) throws Parser.UserInputException {
        if (index < 0 || index >= this.size()) {
            throw new Parser.UserInputException("Invalid Index");
        }
        this.get(index).markDone();
        String msg = "Nice! I've marked this task as done:\n";
        msg += this.get(index).toString() + "\n";
        msg += "Now you have " + Integer.toString(this.size())
                + " tasks in the list.";
        return msg;
    }

    public String delete(int index) throws Parser.UserInputException {
        if (index < 0 || index >= this.size()) {
            throw new Parser.UserInputException("Invalid index");
        }
        Task deleted = this.remove(index);
        String msg = "Noted. I've removed this task: \n";
        msg += deleted + "\n";
        msg += "Now you have " + this.size() + " tasks in the list.";
        return msg;
    }
}
