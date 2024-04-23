package com.zinu.account_manager.exception;

public class EmptyResponseException extends RuntimeException {
    public EmptyResponseException(String message) {
        super(message);
    }
}
