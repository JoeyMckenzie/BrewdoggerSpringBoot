package com.brewdogger.beer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Could not create user")
public class BrewdoggerUserCreationException extends RuntimeException {
    public BrewdoggerUserCreationException() {
        super();
    }

    public BrewdoggerUserCreationException(String message) {
        super(message);
    }

    public BrewdoggerUserCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
