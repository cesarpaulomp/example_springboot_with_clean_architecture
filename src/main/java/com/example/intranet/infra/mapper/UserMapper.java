package com.example.intranet.infra.mapper;

import org.springframework.stereotype.Component;

import com.example.intranet.domain.model.User;
import com.example.intranet.infra.persistence.jpaEntity.JpaUser;

@Component
public class UserMapper {
    public JpaUser toJpaUser(User user) {
        if (user == null) return null;
        var jpa = new JpaUser();
        jpa.setId(user.id());
        jpa.setName(user.name());
        jpa.setEmail(user.email());
        jpa.setPassword(user.password());
        jpa.setCreatedAt(user.createdAt());
        return jpa;
    }

    public User toDomain(JpaUser jpaUser) {
        if (jpaUser == null) return null;
        return new User(
            jpaUser.getId(),
            jpaUser.getName(),
            jpaUser.getEmail(),
            jpaUser.getPassword(),
            jpaUser.getCreatedAt()
        );
    }
}
