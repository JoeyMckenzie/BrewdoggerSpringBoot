package com.brewdogger.beer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No beer found with that id")
public class BeerNotFoundException extends RuntimeException {

    public BeerNotFoundException(String message) {
        super(message);
    }
}
