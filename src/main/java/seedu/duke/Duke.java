package seedu.duke;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Activities.Task> taskList;
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
//        Duke.echo();
        list();
    }
    
    private static void echo() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while(!input.equals("bye")){
            System.out.println(input);
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again!");
    }

    private static void list() {
        taskList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while(!input.equals("bye")){
            if(input.startsWith("done")){
                if(input.length() <= 5) {
                    System.out.println("Please enter index of task after \'done\'");
                }else{
                    try {
                        int index = Integer.parseInt(input.substring(5)) - 1;
                        if(index < 0 || index >= taskList.size()){
                            System.out.println("Invalid index");
                        }else{
                            taskList.get(index).markDone();
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(taskList.get(index).toString());
                        }
                    } catch (NumberFormatException e){
                        System.out.println("Please enter task index");
                    }
                }
            }else if(!input.equals("list")){
                try {
                    addActivity(input);
                } catch (UserInputException e) {
                    System.out.println(e);
                }
            }else{
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < taskList.size(); i++){
                    System.out.print(i + 1);
                    System.out.println(". " + taskList.get(i).toString());
                }
            }
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again!");
    }

    private static Boolean addActivity(String input) throws UserInputException {
        boolean added = false;
        if(input.startsWith("todo")) {
            if(input.length() <= 5) {
                throw new UserInputException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            input = input.substring(5);
            taskList.add(new Activities.ToDo(input));
            added = true;
        } else if (input.startsWith("deadline")) {
            if(input.length() <= 9) {
                throw new UserInputException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            input = input.substring(9);
            if(!input.contains(" /by ")) {
                throw new UserInputException("☹ OOPS!!! A deadline must have a time specified.");
            }
            String name = input.split(" /by ", 2)[0];
            String time = input.split(" /by ", 2)[1];
            taskList.add(new Activities.Deadline(name, time));
            added = true;
        } else if (input.startsWith("event")) {
            if(input.length() <= 6) {
                throw new UserInputException("☹ OOPS!!! The description of a event cannot be empty.");
            }
            input = input.substring(6);
            if(!input.contains(" /at ")) {
                throw new UserInputException("☹ OOPS!!! A event must have a time specified.");
            }
            String name = input.split(" /at ", 2)[0];
            String time = input.split(" /at ", 2)[1];
            taskList.add(new Activities.Event(name, time));
            added = true;
        }
        if(added){
            System.out.println("Got it. I've added this task: ");
            System.out.println("  " + taskList.get(taskList.size() - 1).toString());
            System.out.println("Now you have " + Integer.toString(taskList.size()) + " task(s) in the list.");
        } else {
            throw new UserInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return added;
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
