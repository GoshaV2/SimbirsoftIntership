package com.simbirsoft.intership.exception;

import org.springframework.http.HttpStatus;

public class AmountPatternException extends RuntimeException {
    private final HttpStatus httpStatus;

    public AmountPatternException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
