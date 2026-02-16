package com.example.intranet.application.usecase;

import com.example.intranet.application.ports.in.AuthenticateUserUseCase;
import com.example.intranet.application.ports.in.input.AuthenticateUserInput;
import com.example.intranet.application.ports.out.PasswordEncoder;
import com.example.intranet.application.ports.out.SecurityTokenGeneration;
import com.example.intranet.application.ports.out.UserRepository;
import com.example.intranet.application.ports.out.output.SecurityTokenOutput;
import com.example.intranet.application.usecase.exception.UserAuthenticationFailException;
import com.example.intranet.domain.model.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthenticateUserUseCaseImpl implements AuthenticateUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SecurityTokenGeneration securityTokenGeneration;

    @Override
    public SecurityTokenOutput authenticate(AuthenticateUserInput request) throws UserAuthenticationFailException {
        request.validate();
        
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UserAuthenticationFailException());

        if (!passwordEncoder.matches(request.password(), user.password())) {
            throw new UserAuthenticationFailException();
        }

        return securityTokenGeneration.generateToken(user.id());
    }
}
