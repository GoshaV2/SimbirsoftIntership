package com.simbirsoft.intership.exception;

import org.springframework.http.HttpStatus;

public class UserHasTaskException extends AmountPatternException {
    public UserHasTaskException(long userId) {
        super(String.format("User with id=%d can't be removed from project while has tasks", userId),
                HttpStatus.CONFLICT);
    }
}
