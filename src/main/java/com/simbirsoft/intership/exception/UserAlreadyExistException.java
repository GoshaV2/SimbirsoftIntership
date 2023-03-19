package com.simbirsoft.intership.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistException extends AmountPatternException {
    public UserAlreadyExistException(String email) {
        super(String.format("User with email=%s already exist", email),
                HttpStatus.CONFLICT);
    }
}
