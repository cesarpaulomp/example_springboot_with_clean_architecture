package com.example.intranet.application.usecase.exception;

public class UserAuthenticationFailException extends BusinessException {
    public UserAuthenticationFailException() {
        super(401, "USER_AUTHENTICATION_FAILED");
    }
}
