package com.simbirsoft.intership.exception;

import org.springframework.http.HttpStatus;

public class NotFoundTaskException extends AmountPatternException {
    public NotFoundTaskException(long taskId, long projectId) {
        super(String.format("Not found task with id=%d in project with id=%d", taskId, projectId),
                HttpStatus.NOT_FOUND);
    }
}
