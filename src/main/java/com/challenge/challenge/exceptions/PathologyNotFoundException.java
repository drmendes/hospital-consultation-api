package com.challenge.challenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PathologyNotFoundException extends RuntimeException {
    public PathologyNotFoundException(String message) {
        super(message);
    }
}
