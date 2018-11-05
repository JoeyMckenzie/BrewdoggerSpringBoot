package com.brewdogger.beer.exception;

public class BrewdoggerAuthenticationException extends RuntimeException {
    public BrewdoggerAuthenticationException() {
    }

    public BrewdoggerAuthenticationException(String message) {
        super(message);
    }

    public BrewdoggerAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
