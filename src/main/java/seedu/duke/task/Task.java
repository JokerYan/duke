package seedu.duke.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Super class of all kinds of tasks, with the basic functionality that all
 * tasks share.
 */
public class Task {
    /**
     * The name of the task.
     */
    protected String name;

    /**
     * The flag whether is task is already done. Can only be set from false
     * to true.
     */
    protected boolean isDone;

    /**
     * The type of the task, following the enumeration declared.
     */
    protected TaskType taskType;

    /**
     * A date format that is shared by all tasks to parse and out the date
     * involved in the task.
     */
    protected static SimpleDateFormat format =
            new SimpleDateFormat("dd/MM/yyyy HHmm");


    /**
     * The enumeration of all task type.
     */
    public enum TaskType {
        ToDo, Deadline, Event
    }

    /**
     * Instantiation of a task with the name and the default false value
     * if isDone attribute.
     *
     * @param name the name of the task
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Mark the isDone as true.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Get the status whether the task is done.
     *
     * @return the isDone attribute
     */
    public boolean getDone() {
        return this.isDone;
    }

    /**
     * The function that returns a human readable string of the basic
     * information of the task.
     *
     * @return the human readable string of the basic information the
     * task.
     */
    protected String getStatus() {
        if (this.isDone) {
            return "[\u2713] " + this.name;
        } else {
            return "[\u2718] " + this.name;
        }
    }

    public TaskType getTaskType() {
        return this.taskType;
    }

    /**
     * The function to convert the task to a human readable string.
     * This will return the same string as the get status function for
     * a basic task, but is overridden by more advanced task class to
     * add more information.
     *
     * @return a human readable string that contains all important
     * information of a task.
     */
    public String toString() {
        return this.getStatus();
    }

    /**
     * The function returns a string that contains full information of
     * the task which is used to be stored in a file for future usage.
     *
     * @return a string containing full information of the task.
     */
    public String toFileString() {
        return this.toString();
    }

    /**
     * The function is used to parse the input string to a Date that is
     * used by the tasks with time involved. The function can be called
     * before the initialization of a Task so that the Data can be directly
     * passed to the constructor.
     *
     * @param dateString an input string to be parsed
     * @return parsed result from the input string
     * @throws ParseException an exception when the parsing is failed,
     * most likely due to a wrong format
     */
    public static Date parseDate(String dateString) throws ParseException {
//            System.out.println(dateString);
        return format.parse(dateString);
    }

    /**
     * The function checks whether this task, when converted to string,
     * contains the keyword specified.
     *
     * @param keyword search target string
     * @return a flag whether the keyword is found in the task string
     */
    public boolean matchKeyword(String keyword) {
        return this.toString().contains(keyword);
    }

}
