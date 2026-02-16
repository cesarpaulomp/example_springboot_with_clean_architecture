package com.example.intranet.infra.persistence.jpaEntity;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "app_user")
public class JpaUser {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    @Column(updatable = false)
    private Instant createdAt;
}
