package seedu.duke;

import java.io.*;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static TaskList taskList;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        run();
    }

    private static void run() {
        taskList = Storage.readTasks();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            if (input.startsWith("done")) {
                if (input.length() <= 5) {
                    System.out.println("Please enter index of task after \'done\'");
                } else {
                    try {
                        int index = Integer.parseInt(input.substring(5)) - 1;
                        System.out.println(taskList.markDone(index));
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter task index");
                    }
                }
            } else if (input.startsWith("delete")) {
                if (input.length() <= 7) {
                    System.out.println("Please enter index of task after \'delete\'");
                } else {
                    try {
                        int index = Integer.parseInt(input.substring(7)) - 1;
                        System.out.println(taskList.delete(index));
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter task index");
                    }
                }
            } else if (input.startsWith("find ")) {
                if (input.length() <= 5) {
                    System.out.println("Please enter keyword for searching after \'find\'");
                } else {
                    String keyword = input.split(" ", 2)[1];
                    System.out.println(taskList.findKeyword(keyword));
                }
            } else if (!input.equals("list")) {
                try {
                    System.out.println(taskList.addTask(input));
                } catch (UserInputException e) {
                    System.out.println(e);
                }
            } else {
                System.out.println(taskList);
            }
            input = scanner.nextLine();
        }
        Storage.saveTasks(taskList);
        System.out.println("Bye. Hope to see you again!");
    }

    public static class UserInputException extends Exception {
        private String msg;
        public UserInputException(String msg) {
            super();
            this.msg = msg;
        }

        public String toString(){
            return msg;
        }
    }
}
