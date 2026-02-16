package com.example.intranet.interfaces.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.intranet.application.ports.in.AuthenticateUserUseCase;
import com.example.intranet.application.ports.in.CreateUserUseCase;
import com.example.intranet.application.ports.in.input.AuthenticateUserInput;
import com.example.intranet.application.ports.in.input.CreateUserInput;
import com.example.intranet.application.ports.out.output.SecurityTokenOutput;
import com.example.intranet.application.usecase.exception.UserAuthenticationFailException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/auth", produces = "application/json")
public class AuthController {
    private final CreateUserUseCase createUserUseCase;
    private final AuthenticateUserUseCase authenticateUserUseCase;

    @PostMapping(consumes = "application/json", value = "/signup")
    public void createUser(@RequestBody @Validated CreateUserInput request) {
        createUserUseCase.createUser(request);
    }

    @PostMapping(consumes = "application/json", value = "/login")
    public ResponseEntity<SecurityTokenOutput> login(@RequestBody AuthenticateUserInput request)
            throws UserAuthenticationFailException {
        SecurityTokenOutput response = authenticateUserUseCase.authenticate(request);
        return ResponseEntity.ok(response);
    }
}
