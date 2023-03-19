package com.simbirsoft.intership.exception;

import org.springframework.http.HttpStatus;

public class OwnerDeletingException extends AmountPatternException {
    public OwnerDeletingException(long projectId, long ownerId) {
        super(String.format("Can't delete owner with id=%d from project with id=%d", ownerId, projectId),
                HttpStatus.CONFLICT);
    }
}
