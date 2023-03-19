package com.simbirsoft.intership.exception;

import org.springframework.http.HttpStatus;

public class AlreadyHasPermissionException extends AmountPatternException {
    public AlreadyHasPermissionException(long projectId, long userId) {
        super(String.format("User with id=%d already has permission to project with id=%d", projectId, userId),
                HttpStatus.CONFLICT);
    }
}
