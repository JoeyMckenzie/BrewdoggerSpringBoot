package com.brewdogger.beer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No brewery found with that id")
public class BreweryNotFoundException extends RuntimeException {
    public BreweryNotFoundException(String message) {
        super(message);
    }
}
