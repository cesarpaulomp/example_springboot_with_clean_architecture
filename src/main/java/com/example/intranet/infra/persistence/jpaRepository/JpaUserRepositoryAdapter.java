package com.example.intranet.infra.persistence.jpaRepository;

import org.springframework.stereotype.Component;

import com.example.intranet.application.ports.out.UserRepository;
import com.example.intranet.domain.model.User;
import com.example.intranet.infra.mapper.UserMapper;

import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
@Component
public class JpaUserRepositoryAdapter implements UserRepository {
    private final JpaUserRepository jpaUserRepository;
    private final UserMapper userMapper;    

    @Override
    public void createUser(User user) {
        jpaUserRepository.save(userMapper.toJpaUser(user));
    }
    
    @Override
    public Optional<User> findByEmail(String email) {
        var jpaUser = jpaUserRepository.findByEmail(email).orElse(
            null);
        return Optional.ofNullable(userMapper.toDomain(jpaUser));
    }

    @Override
    public Optional<User> findById(String id) {
        var jpaUser = jpaUserRepository.findById(id).orElse(null);
        return Optional.ofNullable(userMapper.toDomain(jpaUser));
    }
}
