package com.simbirsoft.intership.exception;

import org.springframework.http.HttpStatus;

public class NotFoundPermissionException extends AmountPatternException {
    public NotFoundPermissionException(long userId, long projectId) {
        super(String.format("User with id=%d dont have permission to project with id=%d", userId, projectId),
                HttpStatus.UNAUTHORIZED);
    }
}
