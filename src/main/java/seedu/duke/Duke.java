package seedu.duke;

import java.util.Scanner;

public class Duke {
    private static TaskList taskList;
    private static UI ui;

    public static void main(String[] args) {
        ui = new UI();
        run();
    }

    public static TaskList getTaskList() {
        return taskList;
    }

    public static UI getUI() {
        return ui;
    }

    private static void run() {
        taskList = Storage.readTasks();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Command command = Parser.parseCommand(input);
        while (!(command instanceof Command.ExitCommand)) {
            command.execute();
            input = scanner.nextLine();
            command = Parser.parseCommand(input);
        }
        Storage.saveTasks(taskList);
        ui.showMessage("Bye. Hope to see you again!");
    }
}
