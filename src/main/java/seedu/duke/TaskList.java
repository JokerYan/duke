package seedu.duke;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class TaskList extends ArrayList<Task> {
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
        ArrayList<Task> searchResult = new ArrayList<>();
        for(Task task : this) {
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
        Task deleted = this.remove(index);
        String msg = "Noted. I've removed this task: \n";
        msg += deleted + "\n";
        msg += "Now you have " + this.size() + " tasks in the list.\n";
        return msg;
    }

    public Boolean addTask(String input, boolean log) throws Duke.UserInputException {
        boolean added = false;
        if (input.startsWith("todo")) {
            if (input.length() <= 5) {
                throw new Duke.UserInputException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            input = input.substring(5);
            this.add(new Task.ToDo(input));
            added = true;
        } else if (input.startsWith("deadline")) {
            if (input.length() <= 9) {
                throw new Duke.UserInputException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            input = input.substring(9);
            if (!input.contains(" /by ")) {
                throw new Duke.UserInputException("☹ OOPS!!! A deadline must have a time specified.");
            }
            String name = input.split(" /by ", 2)[0];
            String timeString = input.split(" /by ", 2)[1];
            try {
                Date time = Task.parseDate(timeString);
                this.add(new Task.Deadline(name, time));
                added = true;
            } catch (ParseException e) {
                throw new Duke.UserInputException("Wrong date/time format. Write date/time in dd/MM/yyyy HHmm format.");
            }
        } else if (input.startsWith("event")) {
            if (input.length() <= 6) {
                throw new Duke.UserInputException("☹ OOPS!!! The description of a event cannot be empty.");
            }
            input = input.substring(6);
            if (!input.contains(" /at ")) {
                throw new Duke.UserInputException("☹ OOPS!!! A event must have a time specified.");
            }
            String name = input.split(" /at ", 2)[0];
            String timeString = input.split(" /at ", 2)[1];
            Date time = null;
            try {
                time = Task.parseDate(timeString);
                this.add(new Task.Event(name, time));
                added = true;
            } catch (ParseException e) {
                throw new Duke.UserInputException("Wrong date/time format. Write date/time in dd/MM/yyyy HHmm format.");
            }
        }
        if (added) {
            if (log) {
                System.out.println("Got it. I've added this task: ");
                System.out.println("  " + this.get(this.size() - 1).toString());
                System.out.println("Now you have " + Integer.toString(this.size()) + " task(s) in the list.");
            }
        } else {
            throw new Duke.UserInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return added;
    }
}
