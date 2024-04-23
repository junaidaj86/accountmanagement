package com.zinu.account_manager.exception;

public class DriverNotFoundException extends RuntimeException{
    public DriverNotFoundException() {
        super("Driver not found");
    }

    public DriverNotFoundException(String message) {
        super(message);
    }

    public DriverNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
