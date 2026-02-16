package com.example.intranet.interfaces.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.intranet.application.usecase.exception.BusinessException;
import com.example.intranet.interfaces.dto.DefaultErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<DefaultErrorResponse> handleBusinessException(BusinessException ex) {
        DefaultErrorResponse errorResponse = new DefaultErrorResponse(
                400,
                ex.getMessage());

        return ResponseEntity
                .status(400)
                .body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<DefaultErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
        DefaultErrorResponse errorResponse = new DefaultErrorResponse(
                400,
                ex.getMessage());

        return ResponseEntity
                .status(400)
                .body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DefaultErrorResponse> handleGenericException(Exception ex) {
        DefaultErrorResponse errorResponse = new DefaultErrorResponse(
                500,
                "INTERNAL_SERVER_ERROR");

        return ResponseEntity
                .status(500)
                .body(errorResponse);
    }
}
