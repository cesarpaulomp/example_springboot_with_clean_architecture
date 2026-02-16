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
@Table(name = "post")
public class JpaPost {
    @Id
    private String id;
    private String content;
    private String authorId;
    @Column(updatable = false)
    private Instant createdAt;
    private Instant updatedAt;
}
