package com.example.intranet.infra.security;

import org.springframework.stereotype.Component;

import com.example.intranet.application.ports.out.PasswordEncoder;

@Component
public class BCryptPasswordEncoderAdapter implements PasswordEncoder {
    private final org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder bCryptPasswordEncoder;

    public BCryptPasswordEncoderAdapter() {
        this.bCryptPasswordEncoder = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
    }

    @Override
    public String encode(String rawPassword) {
        return bCryptPasswordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }
    
}
