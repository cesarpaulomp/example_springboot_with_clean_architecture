package com.example.intranet.interfaces.controllers;

import org.springframework.http.ResponseEntity;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/auth", produces = "application/json")
@Tag(name = "Authentication", description = "Endpoints for user registration and authentication")
public class AuthController {
    private final CreateUserUseCase createUserUseCase;
    private final AuthenticateUserUseCase authenticateUserUseCase;

    @Operation(
        summary = "Register new user",
        description = "Creates a new user in the system with the provided data"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "User created successfully"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid data provided",
            content = @Content
        )
    })
    @PostMapping(consumes = "application/json", value = "/signup")
    public void createUser(@RequestBody CreateUserInput request) {
        createUserUseCase.createUser(request);
    }

    @Operation(
        summary = "Authenticate user",
        description = "Performs user login and returns a JWT token for authentication"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Login successful",
            content = @Content(schema = @Schema(implementation = SecurityTokenOutput.class))
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Invalid credentials",
            content = @Content
        )
    })
    @PostMapping(consumes = "application/json", value = "/login")
    public ResponseEntity<SecurityTokenOutput> login(@RequestBody AuthenticateUserInput request)
            throws UserAuthenticationFailException {
        SecurityTokenOutput response = authenticateUserUseCase.authenticate(request);
        return ResponseEntity.ok(response);
    }
}
