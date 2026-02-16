package com.example.intranet.application.usecase.exception;

public class RecordNotFoundException extends BusinessException {
    public RecordNotFoundException() {
        super(404, "RECORD_NOT_FOUND");
    }
}
