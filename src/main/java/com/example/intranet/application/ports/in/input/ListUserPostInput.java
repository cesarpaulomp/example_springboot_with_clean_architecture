package com.example.intranet.application.ports.in.input;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public record ListUserPostInput(
    String authorId,
    Instant startedAt,
    Instant endedAt,
    Integer page,
    Integer size
) {
    public boolean validate() {
        return authorId != null && !authorId.isBlank();
    }
    
    public Instant getStartedAt() {
        if (startedAt == null) {
            return Instant.now().minus(1, ChronoUnit.DAYS);
        }
        return startedAt;
    }

    public Instant getEndedAt() {
        if (endedAt == null) {
            return Instant.now();
        }
        return endedAt;
    }

    public Integer getPage() {
        if (page == null || page < 0) {
            return 0;
        }
        return page;
    }

    public Integer getSize() {
        if (size == null || size <= 0) {
            return 10;
        }
        return size;
    }
}