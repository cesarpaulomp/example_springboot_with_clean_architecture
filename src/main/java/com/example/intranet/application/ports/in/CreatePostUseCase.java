package com.example.intranet.application.ports.in;

import com.example.intranet.application.ports.in.input.CreatePostInput;
import com.example.intranet.domain.model.Post;

public interface CreatePostUseCase {
    Post createPost(CreatePostInput request);
}
