package com.example.intranet.application.usecase;

import java.util.List;

import com.example.intranet.application.ports.in.GetPostUseCase;
import com.example.intranet.application.ports.in.input.ListUserPostInput;
import com.example.intranet.application.ports.out.PostRepository;
import com.example.intranet.application.ports.out.output.PagedListOutput;
import com.example.intranet.application.usecase.exception.RecordNotFoundException;
import com.example.intranet.domain.model.Post;

public final class GetPostUseCaseImpl implements GetPostUseCase {

  private final PostRepository postRepository;

  public GetPostUseCaseImpl(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public PagedListOutput<Post> getPosts(ListUserPostInput command) {
    return postRepository.listUserPosts(command, "createdAt");
  }

  public Post getPostById(String id) throws RecordNotFoundException {
    return postRepository.getPostById(id);
  }
}
