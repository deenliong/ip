package duke.task;

import duke.exception.DukeException;

public class Todo extends Task {

    public Todo(String description) throws DukeException {
        super(description);
    }

    @Override
    public String toString() {

        return "[T]" + super.toString();
    }
}