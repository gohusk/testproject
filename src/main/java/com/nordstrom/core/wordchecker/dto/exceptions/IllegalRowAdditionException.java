package com.nordstrom.core.wordchecker.dto.exceptions;

/**
 * Created by plavelle on 9/20/2016.
 */
public class IllegalRowAdditionException extends RuntimeException {

    public IllegalRowAdditionException(String message) {
        throw new RuntimeException(message);
    }
}
