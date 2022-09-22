package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static duke.FileManager.*;

public class Duke {

    public static void printIntroMessage(ArrayList<Task> tasks) {
        System.out.println("Hello! I'm Duke, your personal task manager!");
        if (tasks.size() > 0) {
            System.out.println("Here are the current tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        } else {
            System.out.println("You have no tasks in your list. Try adding some!");
        }
    }

    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        // attempts to find file and add to list. if not found, creates a new file in a new directory
        try {
            loadInputFile(tasks);
        } catch (FileNotFoundException e) {
            createNewFile();
        }

        printIntroMessage(tasks);

        do {
            line = in.nextLine();
            String type = TaskManager.getTaskType(line);

            if (type.equals("bye")) {
                System.out.println("\tBye. Hope to see you again soon!");
                clearCurrentFile();
                saveNewList(tasks);
                return;
            }
            switch (type) {
            case "list":
                TaskManager.printTaskList(tasks);
                break;
            case "delete":
                try {
                    TaskManager.deleteTask(tasks, line);
                } catch (NumberFormatException e) {
                    System.out.println("\tPlease input the task number that you want to delete.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("\tThere is no such item in your Task List.");
                }
                break;
            case "mark":
                try {
                    TaskManager.markTask(tasks, line);
                } catch (NumberFormatException e) {
                    System.out.println("\tPlease input the task number that you want to mark.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("\tThere is no such item in your Task List.");
                }
                break;
            case "unmark":
                try {
                    TaskManager.unmarkTask(tasks, line);
                } catch (NumberFormatException e) {
                    System.out.println("\tPlease input the task number that you want to mark.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("\tThere is no such item in your Task List.");
                }
                break;
            case "total":
                int total = tasks.size();
                if (total == 1) {
                    System.out.println("\tYou have 1 task");
                } else {
                    System.out.println("\tYou have " + total + " tasks!!!");
                }
                break;
            case "todo":
                try {
                    String details = TaskManager.getTaskDetails(line);
                    Task t = new Todo(details);
                    tasks.add(t);
                    TaskManager.printSuccessfulAdd(tasks);
                } catch (ArrayIndexOutOfBoundsException | DukeException e) {
                    System.out.println("\t☹ OOPS!!! The description of a todo cannot be empty. Please tell me what you want to do");
                    System.out.println("\tExample: todo (return book)");
                }
                break;
            case "deadline":
                try {
                    String details = TaskManager.getTaskDetails(line);
                    String[] breakBy = details.split("/by", 2);
                    String detail = breakBy[0];
                    String by = breakBy[1];
                    Task d = new Deadline(detail, by);
                    tasks.add(d);
                    TaskManager.printSuccessfulAdd(tasks);
                } catch (ArrayIndexOutOfBoundsException | DukeException e) {
                    System.out.println("\tPlease tell me when is the deadline.");
                    System.out.println("\tExample: deadline (return book) /by (Sunday)");
                }
                break;
            case "event":
                try {
                    String details = TaskManager.getTaskDetails(line);
                    String[] breakAt = details.split("/at", 2);
                    String detail = breakAt[0];
                    String at = breakAt[1];
                    Task e = new Event(detail, at);
                    tasks.add(e);
                    TaskManager.printSuccessfulAdd(tasks);
                } catch (ArrayIndexOutOfBoundsException | DukeException e) {
                    System.out.println("\tPlease tell me when is the event.");
                    System.out.println("\tExample: event (borrow book) /at (library)");
                }
                break;
            default:
                System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } while (!line.equals("bye"));
    }
}



