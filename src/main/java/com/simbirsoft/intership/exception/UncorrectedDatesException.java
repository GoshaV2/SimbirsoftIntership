package com.simbirsoft.intership.exception;

import org.springframework.http.HttpStatus;

import java.sql.Date;

public class UncorrectedDatesException extends AmountPatternException {
    public UncorrectedDatesException(Date startDate, Date endDate) {
        super(String.format("Dates in creating realise are uncorrected: start-%s, end-%s",
                        startDate.toString(), endDate.toString()),
                HttpStatus.BAD_REQUEST);
    }
}
