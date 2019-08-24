package seedu.duke;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> taskList;
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

    public static class Task {
        private String name;
        private boolean done;
        public Task(String name) {
            this.name = name;
            this.done = false;
        }

        public void markDone() {
            this.done = true;
        }

        public boolean getDone() {
            return this.done;
        }

        public String getStatus() {
            if(this.done){
                return "[\u2713] " + this.name;
            }else{
                return "[\u2718] " + this.name;
            }
        }
    }

    private static void list() {
        taskList = new ArrayList<Task>();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while(!input.equals("bye")){
            if(input.startsWith("done")){
                if(input.length() <= 5) {
                    System.out.println("Please enter index of task after \'done\'");
                }else{
                    int index = Integer.parseInt(input.substring(5)) - 1;
                    if(index < 0 || index >= taskList.size()){
                        System.out.println("Invalid index");
                    }else{
                        taskList.get(index).markDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(taskList.get(index).getStatus());
                    }
                }
            }else if(!input.equals("list")){
                taskList.add(new Task(input));
                System.out.println("added: " + input);
            }else{
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < taskList.size(); i++){
                    System.out.print(i + 1);
                    System.out.println(". " + taskList.get(i).getStatus());
                }
            }
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again!");
    }
}
