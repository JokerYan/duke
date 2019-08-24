package seedu.duke;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static private ArrayList<String> inputList;
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

    private static void echo(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while(!input.equals("bye")){
            System.out.println(input);
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again!");
    }

    private static void list(){
        inputList = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while(!input.equals("bye")){
            if(!input.equals("list")){
                inputList.add(input);
                System.out.println("added: " + input);
            }else{
                for(int i = 0; i < inputList.size(); i++){
                    System.out.print(i + 1);
                    System.out.println(". " + inputList.get(i));
                }
            }
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again!");
    }
}
