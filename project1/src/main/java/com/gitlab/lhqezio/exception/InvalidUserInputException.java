package com.gitlab.lhqezio.exception;

public class InvalidUserInputException extends Exception {
    public InvalidUserInputException(String message) {
        super(message);
    }
    public InvalidUserInputException() {
        super();
    }
}