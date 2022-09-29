package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidDeadlineInputException;
import duke.task.TaskList;

public class DeadlineCommand extends Command {
    public DeadlineCommand(String input) {
        super(input);
    }

    public static final String KEYWORD = "deadline";

    @Override
    public TaskList execute(TaskList tasks, Storage storage, Ui ui, String fullCommand) throws InvalidDeadlineInputException {
        TaskList.addDeadline(tasks, fullCommand);
        return tasks;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
