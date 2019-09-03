package seedu.duke;

import java.util.ArrayList;

public class TaskList extends ArrayList<Activities.Task> {
    @Override
    public String toString() {
        if(this.size() == 0) {
            return "There is nothing in your list.";
        }
        String msg = "Here are the tasks in your list:\n";
        for(int i = 0; i < this.size(); i++) {
            msg += i + 1;
            msg += ". " + this.get(i) + "\n";
        }
        return msg;
    }

    public String findKeyword(String keyword) {
        ArrayList<Activities.Task> searchResult = new ArrayList<>();
        for(Activities.Task task : this) {
            if(task.matchKeyword(keyword)) {
                searchResult.add(task);
            }
        }
        String msg = "";
        if(searchResult.size() > 0) {
            msg += "Here are the matching tasks in your list:\n";
            for(int i = 0; i < searchResult.size(); i++) {
                msg += Integer.toString(i + 1) + ". " + searchResult.get(i) + "\n";
            }
        } else {
            msg += "There is no matching task in your list.\n";
        }
        return msg;
    }

    public String markDone(int index) {
        if(index < 0 || index >= this.size()) {
            return "Invalid Index";
        }
        this.get(index).markDone();
        String msg = "Nice! I've marked this task as done:\n";
        msg += this.get(index).toString() + "n";
        msg += "Now you have " + Integer.toString(this.size())
                + " tasks in the list.\n";
        return msg;
    }

    public String delete(int index) {
        if(index < 0 || index >= this.size()){
            return "Invalid index";
        }
        Activities.Task deleted = this.remove(index);
        String msg = "Noted. I've removed this task: \n";
        msg += deleted + "\n";
        msg += "Now you have " + this.size() + " tasks in the list.\n";
        return msg;
    }
}
