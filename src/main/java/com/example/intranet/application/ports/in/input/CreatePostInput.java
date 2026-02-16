package com.example.intranet.application.ports.in.input;

public record CreatePostInput(String content, String authorId) {
    
    public void validate() {
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("Content is required");
        }
        if (content.length() > 5000) {
            throw new IllegalArgumentException("Content cannot exceed 5000 characters");
        }
        if (authorId == null || authorId.isBlank()) {
            throw new IllegalArgumentException("Author ID is required");
        }
    }
}
