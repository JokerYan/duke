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
        taskList = new TaskList();
        readTasks();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while(!input.equals("bye")){
            if(input.startsWith("done")){
                if(input.length() <= 5) {
                    System.out.println("Please enter index of task after \'done\'");
                } else {
                    try {
                        int index = Integer.parseInt(input.substring(5)) - 1;
                        System.out.println(taskList.markDone(index));
                    } catch (NumberFormatException e){
                        System.out.println("Please enter task index");
                    }
                }
            }else if(input.startsWith("delete")){
                if(input.length() <= 7) {
                    System.out.println("Please enter index of task after \'delete\'");
                } else {
                    try {
                        int index = Integer.parseInt(input.substring(7)) - 1;
                        System.out.println(taskList.delete(index));
                    } catch (NumberFormatException e){
                        System.out.println("Please enter task index");
                    }
                }
            } else if(input.startsWith("find ")) {
                if(input.length() <= 5) {
                    System.out.println("Please enter keyword for searching after \'find\'");
                } else {
                    String keyword = input.split(" ", 2)[1];
                    System.out.println(taskList.findKeyword(keyword));
                }
            } else if(!input.equals("list")) {
                try {
                    addActivity(input, true);
                } catch (UserInputException e) {
                    System.out.println(e);
                }
            } else {
                System.out.println(taskList);
            }
            input = scanner.nextLine();
        }
        saveTasks();
        System.out.println("Bye. Hope to see you again!");
    }

    private static Boolean addActivity(String input, boolean log) throws UserInputException {
        boolean added = false;
        if (input.startsWith("todo")) {
            if (input.length() <= 5) {
                throw new UserInputException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            input = input.substring(5);
            taskList.add(new Task.ToDo(input));
            added = true;
        } else if (input.startsWith("deadline")) {
            if (input.length() <= 9) {
                throw new UserInputException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            input = input.substring(9);
            if (!input.contains(" /by ")) {
                throw new UserInputException("☹ OOPS!!! A deadline must have a time specified.");
            }
            String name = input.split(" /by ", 2)[0];
            String timeString = input.split(" /by ", 2)[1];
            try {
                Date time = Task.parseDate(timeString);
                taskList.add(new Task.Deadline(name, time));
                added = true;
            } catch (ParseException e) {
                throw new UserInputException("Wrong date/time format. Write date/time in dd/MM/yyyy HHmm format.");
            }
        } else if (input.startsWith("event")) {
            if (input.length() <= 6) {
                throw new UserInputException("☹ OOPS!!! The description of a event cannot be empty.");
            }
            input = input.substring(6);
            if (!input.contains(" /at ")) {
                throw new UserInputException("☹ OOPS!!! A event must have a time specified.");
            }
            String name = input.split(" /at ", 2)[0];
            String timeString = input.split(" /at ", 2)[1];
            Date time = null;
            try {
                time = Task.parseDate(timeString);
                taskList.add(new Task.Event(name, time));
                added = true;
            } catch (ParseException e) {
                throw new UserInputException("Wrong date/time format. Write date/time in dd/MM/yyyy HHmm format.");
            }
        }
        if (added) {
            if (log) {
                System.out.println("Got it. I've added this task: ");
                System.out.println("  " + taskList.get(taskList.size() - 1).toString());
                System.out.println("Now you have " + Integer.toString(taskList.size()) + " task(s) in the list.");
            }
        } else {
            throw new UserInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return added;
    }

    private static void readTasks() {
        String subDir = "";
        String workingDir = System.getProperty("user.dir");
        if(workingDir.endsWith("text-ui-test")) {
            subDir = ".";
        }
        FileInputStream in;
        File file = new File("." + subDir + "\\data\\duke.txt");
        try {
            in = new FileInputStream(file);
            Scanner scanner = new Scanner(in);
            ArrayList<Boolean> doneList = new ArrayList<>();
            while(scanner.hasNextLine()) {
                String input = scanner.nextLine();
                if(input.length() <= 2) {
                    throw new UserInputException("Invalid Save File!");
                }
                if(input.startsWith("1")) {
                    doneList.add(true);
                } else if(input.startsWith("0")){
                    doneList.add(false);
                } else {
                    throw new UserInputException("Invalid Save File!");
                }
                input = input.split(" ", 2)[1];
                try {
//                    System.out.println(input);
                    addActivity(input, false);
                } catch (UserInputException e) {
                    throw new UserInputException("Invalid Save File!");
                }
            }
            for(int i = 0; i < taskList.size(); i++) {
                if(doneList.get(i)) {
                    taskList.get(i).markDone();
                }
            }
            System.out.println("Save file successfully loaded...");
            in.close();
        } catch (FileNotFoundException e) {
            return; //it is acceptable if there is no save file
        } catch (IOException e) {
            System.out.println("Read save file IO exception");
        } catch (UserInputException e) {
            System.out.println(e);
            taskList = new TaskList();
        }
    }

    private static void saveTasks() {
        String subDir = "";
        String workingDir = System.getProperty("user.dir");
        if(workingDir.endsWith("text-ui-test")) {
            subDir = ".";
        }
        FileOutputStream out;
        try {
            File file = new File("." + subDir +"\\data\\duke.txt");
            file.createNewFile();
            out = new FileOutputStream(file, false);
            String content = "";
            for(Task task : taskList){
                content += task.toFileString() + "\n";
            }
            out.write(content.getBytes());
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("Output file not found!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Write to output file IO exception!");
        }
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
