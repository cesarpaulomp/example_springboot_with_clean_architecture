package com.example.intranet.domain.model;

import java.time.Instant;

public record User(
    String id,
    String name,
    String email,
    String password,
    Instant createdAt
) {
    
}
