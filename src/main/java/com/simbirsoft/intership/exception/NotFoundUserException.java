package com.simbirsoft.intership.exception;

import org.springframework.http.HttpStatus;

public class NotFoundUserException extends AmountPatternException {
    public NotFoundUserException(long userId) {
        super(String.format("Not found user with id=%d", userId),
                HttpStatus.NOT_FOUND);
    }

    public NotFoundUserException(String userEmail) {
        super(String.format("Not found user with email=%s", userEmail),
                HttpStatus.NOT_FOUND);
    }
}
