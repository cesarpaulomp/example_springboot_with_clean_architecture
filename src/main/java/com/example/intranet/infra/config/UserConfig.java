package com.example.intranet.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.intranet.application.ports.in.AuthenticateUserUseCase;
import com.example.intranet.application.ports.in.CreateUserUseCase;
import com.example.intranet.application.ports.out.PasswordEncoder;
import com.example.intranet.application.ports.out.SecurityTokenGeneration;
import com.example.intranet.application.ports.out.UserRepository;
import com.example.intranet.application.usecase.AuthenticateUserUseCaseImpl;
import com.example.intranet.application.usecase.CreateUserUseCaseImpl;

@Configuration
public class UserConfig {
    @Bean
    CreateUserUseCase createUserUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return new CreateUserUseCaseImpl(
                userRepository, passwordEncoder);
    }

    @Bean
    AuthenticateUserUseCase authenticateUserUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder,
            SecurityTokenGeneration securityTokenGeneration) {
        return new AuthenticateUserUseCaseImpl(userRepository, passwordEncoder, securityTokenGeneration);
    }
}
