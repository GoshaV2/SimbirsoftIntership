package com.simbirsoft.intership.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Unable to find element in database")
public class NotFoundElementException extends RuntimeException{
}
