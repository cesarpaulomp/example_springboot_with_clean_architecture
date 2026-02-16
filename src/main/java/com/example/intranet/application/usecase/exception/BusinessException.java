package com.example.intranet.application.usecase.exception;

public class BusinessException extends Exception {
    public BusinessException(int httpCode, String message) {
        super(message);
    }
}
