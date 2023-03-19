package com.simbirsoft.intership.exception;

import org.springframework.http.HttpStatus;

public class TaskNotDoneException extends AmountPatternException {
    public TaskNotDoneException(long projectId) {
        super(String.format("Project with id=%d can't be closed while all tasks is not done", projectId),
                HttpStatus.CONFLICT);
    }
}
