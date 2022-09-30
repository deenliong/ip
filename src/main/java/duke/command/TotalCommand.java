package duke.command;


import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

public class TotalCommand extends Command {
    public TotalCommand(String input) {
        super(input);
    }

    public static final String KEYWORD = "total";

    @Override
    public TaskList execute(TaskList tasks, Storage storage, Ui ui, String fullCommand) throws DukeException {
        TaskList.totalTask(tasks, fullCommand);
        return tasks;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
