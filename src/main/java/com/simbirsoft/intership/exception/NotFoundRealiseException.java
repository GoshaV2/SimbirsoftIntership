package com.simbirsoft.intership.exception;

import org.springframework.http.HttpStatus;

public class NotFoundRealiseException extends AmountPatternException {
    public NotFoundRealiseException(long realiseId, long projectId) {
        super(String.format("Not found realise with id=%d in project with id=%d", realiseId, projectId),
                HttpStatus.NOT_FOUND);
    }
}
