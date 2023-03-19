package com.simbirsoft.intership.exception;

import org.springframework.http.HttpStatus;

public class RealiseHasTaskException extends AmountPatternException {
    public RealiseHasTaskException(long realiseId) {
        super(String.format("Realise with id=%d has tasks and can't be removed", realiseId),
                HttpStatus.CONFLICT);
    }
}
