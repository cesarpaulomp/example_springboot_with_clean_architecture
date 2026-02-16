package com.example.intranet.application.ports.in;

import com.example.intranet.application.ports.in.input.AuthenticateUserInput;
import com.example.intranet.application.ports.out.output.SecurityTokenOutput;
import com.example.intranet.application.usecase.exception.UserAuthenticationFailException;

public interface AuthenticateUserUseCase {
    SecurityTokenOutput authenticate(AuthenticateUserInput request) throws UserAuthenticationFailException;
}
