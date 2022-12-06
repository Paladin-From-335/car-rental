package com.github.carrental.exception;

public class CarInUseException extends RuntimeException {

    public CarInUseException() {
        super("Impossible to delete rented car");
    }

    public CarInUseException(String message) {
        super(message);
    }
}
