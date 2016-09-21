package com.nordstrom.core.wordchecker.model.exceptions;

/**
 * Exception to be thrown when an attempt to add a row with an incorrect dimension to the puzzle grid
 */
public class IllegalRowAdditionException extends RuntimeException {

    public IllegalRowAdditionException(String message) {
        throw new RuntimeException(message);
    }
}
