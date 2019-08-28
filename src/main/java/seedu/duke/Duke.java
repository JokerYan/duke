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
                addActivity(input);
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

    private static Boolean addActivity(String input) {
        boolean added = false;
        if(input.startsWith("todo ")) {
            taskList.add(new Activities.ToDo(input.substring(5)));
            added = true;
        } else if (input.startsWith("deadline ")) {
            input = input.substring(9);
            String name = input.split(" /by ")[0];
            String time = input.split(" /by ")[1];
            taskList.add(new Activities.Deadline(name, time));
            added = true;
        } else if (input.startsWith("event ")) {
            input = input.substring(6);
            String name = input.split(" /at ")[0];
            String time = input.split(" /at ")[1];
            taskList.add(new Activities.Event(name, time));
            added = true;
        }
        if(added){
            System.out.println("Got it. I've added this task: ");
            System.out.println("  " + taskList.get(taskList.size() - 1).toString());
            System.out.println("Now you have " + Integer.toString(taskList.size()) + " task(s) in the list.");
        } else {
            System.out.println("Invalid task type! Please check again. ");
        }
        return added;
    }
}
