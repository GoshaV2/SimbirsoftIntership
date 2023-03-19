package com.simbirsoft.intership.controller;

import com.simbirsoft.intership.exception.AmountPatternException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {AmountPatternException.class})
    protected ResponseEntity<Object> handleAmountPatternException(
            AmountPatternException exception, WebRequest request) {
        log.error("Amount exception", exception);
        return handleExceptionInternal(exception, new Response(exception.getMessage()),
                new HttpHeaders(), exception.getHttpStatus(), request);
    }
}

class Response {
    private String reason;

    public Response(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
