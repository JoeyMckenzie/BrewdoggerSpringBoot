package com.brewdogger.beer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Improper data")
public class BrewdoggerException extends RuntimeException {

    public BrewdoggerException() {
        super();
    }

    public BrewdoggerException(String message) {
        super(message);
    }

    public BrewdoggerException(String message, Throwable cause) {
        super(message, cause);
    }
}
