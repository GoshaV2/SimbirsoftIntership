package com.simbirsoft.intership.exception;

import org.springframework.http.HttpStatus;

public class NotFoundProjectException extends AmountPatternException {
    public NotFoundProjectException(long projectId, long userId) {
        super(String.format("Project with id=%d and userId=%d not found", projectId, userId),
                HttpStatus.NOT_FOUND);
    }
}
