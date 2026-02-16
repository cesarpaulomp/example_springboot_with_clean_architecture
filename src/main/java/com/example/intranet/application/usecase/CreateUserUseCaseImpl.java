package com.example.intranet.application.usecase;

import java.util.UUID;

import com.example.intranet.application.ports.in.CreateUserUseCase;
import com.example.intranet.application.ports.in.input.CreateUserInput;
import com.example.intranet.application.ports.out.PasswordEncoder;
import com.example.intranet.application.ports.out.UserRepository;
import com.example.intranet.domain.model.User;

public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateUserUseCaseImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createUser(CreateUserInput command) {
        command.validate();
        
        var user = new User(
            UUID.randomUUID().toString(),
            command.name(),
            command.email(),
            passwordEncoder.encode(command.password()),
            java.time.Instant.now()
        );
        userRepository.createUser(user);
    }
}
