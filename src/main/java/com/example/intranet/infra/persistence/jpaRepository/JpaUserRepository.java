package com.example.intranet.infra.persistence.jpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.intranet.infra.persistence.jpaEntity.JpaUser;

import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaRepository<JpaUser, String> {
    Optional<JpaUser> findByEmail(String email);
}
