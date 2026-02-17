package com.example.intranet.infra.security;

import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.example.intranet.application.ports.out.SecurityTokenGeneration;
import com.example.intranet.application.ports.out.output.SecurityTokenOutput;
import com.example.intranet.infra.service.JwtService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class SecurityTokenGenerationAdapter implements SecurityTokenGeneration {

    private final JwtService jwtService;

    @Override
    public SecurityTokenOutput generateToken(String username) {
        return new SecurityTokenOutput(jwtService.generateToken(new HashMap<>(), username),
                jwtService.getJwtExpiration());
    }

}
