package com.example.intranet.domain.model;

import java.time.Instant;

public record Post(String id, String content, String authorId, Instant createdAt, Instant updatedAt) {
}
