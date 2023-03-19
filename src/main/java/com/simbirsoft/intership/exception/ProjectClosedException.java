package com.simbirsoft.intership.exception;

import org.springframework.http.HttpStatus;

public class ProjectClosedException extends AmountPatternException {
    public ProjectClosedException(long projectId, long userId) {
        super(String.format("User with id=%d can't manage project with id=%d", userId, projectId),
                HttpStatus.LOCKED);
    }
}
