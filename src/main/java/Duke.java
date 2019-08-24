package seedu.duke;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        Duke.echo();
    }

    private static void echo(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        while(!input.equals("bye")){
            System.out.println(input);
            input = scanner.next();
        }
        System.out.println("Bye. Hope to see you again!");
    }
}
