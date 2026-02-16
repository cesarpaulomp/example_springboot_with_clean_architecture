package com.example.intranet.application.usecase;

import java.time.Instant;
import java.util.UUID;

import com.example.intranet.application.ports.in.CreatePostUseCase;
import com.example.intranet.application.ports.in.input.CreatePostInput;
import com.example.intranet.application.ports.out.PostRepository;
import com.example.intranet.domain.model.Post;

public final class CreatePostUseCaseImpl implements CreatePostUseCase {

  private final PostRepository postRepository;

  public CreatePostUseCaseImpl(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  @Override
  public Post createPost(CreatePostInput request) {
    request.validate();
    
    Post post = new Post(
        UUID.randomUUID().toString(),
        request.content(),
        request.authorId(),
        Instant.now(), null);
    return postRepository.createPost(post);
  }
}
