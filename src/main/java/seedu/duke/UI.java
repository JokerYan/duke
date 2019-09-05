package seedu.duke;

public class UI {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public UI() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        logo = "Hello from\n" + logo + "\n";
        logo += "What can I do for you?";
        showMessage(logo);
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }

    public void showResponse(String msg) {
        System.out.println("------------------------------");
        System.out.println(msg);
        System.out.println("------------------------------\n");
    }

    public void showError(String msg) {
        System.out.println(ANSI_RED + msg + ANSI_RESET);
    }

}
