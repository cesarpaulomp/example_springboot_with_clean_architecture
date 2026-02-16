package com.example.intranet.application.ports.out;

import com.example.intranet.domain.model.User;

import java.util.Optional;

public interface UserRepository {
    void createUser(User user);
    Optional<User> findByEmail(String email);
}