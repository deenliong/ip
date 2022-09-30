package duke.parser;

import duke.exception.InvalidDeadlineInputException;
import duke.exception.InvalidEventInputException;
import duke.exception.InvalidTaskDescriptionException;

public class Parser {
    public static String getKeyword(String input) {
        String[] words = input.split(" ");
        return words[0];
    }

    public static String getTaskDescription (String line) throws InvalidTaskDescriptionException{
        String[] breakLine = line.trim().split(" ", 2);
        if (breakLine.length == 1 || breakLine[1].isBlank()) {
            throw new InvalidTaskDescriptionException("☹ OOPS!!! The description of a task cannot be empty.");
        } return breakLine[1];
    }

    public static String removeCommand (String input){
        String[] words = input.split(" ", 2);
        return words[1];
    }

    public static String[] parseDeadlineDescription(String input) throws InvalidDeadlineInputException {
        String description = removeCommand(input);
        String[] split = description.split(" /by ");
        if (split.length != 2 || split[0].isBlank() || split[0].isEmpty() || split[1].isBlank() || split[1].isEmpty()) {
            throw new InvalidDeadlineInputException("Please enter a valid deadline");
        } 
        return split;
    }

    // parse event description into details and at
    public static String[] parseEventDescription(String input) throws InvalidEventInputException {
        String description = removeCommand(input);
        String[] split = description.split(" /at ");
        if (split.length != 2 || split[0].isBlank() || split[0].isEmpty() || split[1].isBlank() || split[1].isEmpty()) {
            throw new InvalidEventInputException("Please enter a valid event");
        } 
        return split;
    }

    public static int getTaskId(String input) {
        int inputId = Integer.parseInt(input.replaceAll("[^0-9]", ""));    // gets the id
        return (inputId - 1);
    }

    
}
