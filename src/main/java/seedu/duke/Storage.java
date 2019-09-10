package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.task.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static void saveTasks(TaskList taskList) {
        String subDir = "";
        String workingDir = System.getProperty("user.dir");
        if (workingDir.endsWith("text-ui-test")) {
            subDir = ".";
        }
        FileOutputStream out;
        try {
            File file = new File("." + subDir + "\\data\\duke.txt");
            file.createNewFile();
            out = new FileOutputStream(file, false);
            String content = "";
            for (Task task : taskList) {
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

    public static TaskList readTasks() {
        TaskList taskList = new TaskList();
        String subDir = "";
        String workingDir = System.getProperty("user.dir");
        if (workingDir.endsWith("text-ui-test")) {
            subDir = ".";
        }
        FileInputStream in;
        File file = new File("." + subDir + "\\data\\duke.txt");
        try {
            in = new FileInputStream(file);
            Scanner scanner = new Scanner(in);
            ArrayList<Boolean> doneList = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                if (input.length() <= 2) {
                    throw new StorageException("Invalid Save File!");
                }
                if (input.startsWith("1")) {
                    doneList.add(true);
                } else if (input.startsWith("0")) {
                    doneList.add(false);
                } else {
                    throw new StorageException("Invalid Save File!");
                }
                input = input.split(" ", 2)[1];
                try {
                    Command adddCommand = Parser.parseTask(taskList, input);
                    adddCommand.setSilent();
                    adddCommand.execute();
                } catch (Parser.UserInputException e) {
                    throw new StorageException("Invalid Save File!");
                }
            }
            for (int i = 0; i < taskList.size(); i++) {
                if (doneList.get(i)) {
                    taskList.get(i).markDone();
                }
            }
            Duke.getUI().showMessage("Save file successfully loaded...");
            in.close();
        } catch (FileNotFoundException e) {
            return taskList; //it is acceptable if there is no save file
        } catch (IOException e) {
            Duke.getUI().showError("Read save file IO exception");
        } catch (StorageException e) {
            Duke.getUI().showError(e.toString());
            taskList = new TaskList();
        }
        return taskList;
    }

    public static class StorageException extends Exception {
        private String msg;

        public StorageException(String msg) {
            super();
            this.msg = msg;
        }

        public String toString() {
            return msg;
        }
    }
}
