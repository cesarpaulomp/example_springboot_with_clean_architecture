package com.example.intranet.interfaces.controllers;

import java.time.Instant;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.intranet.application.ports.in.CreatePostUseCase;
import com.example.intranet.application.ports.in.GetPostUseCase;
import com.example.intranet.application.ports.in.input.CreatePostInput;
import com.example.intranet.application.ports.in.input.ListUserPostInput;
import com.example.intranet.application.ports.out.output.PagedListOutput;
import com.example.intranet.application.usecase.exception.RecordNotFoundException;
import com.example.intranet.domain.model.Post;
import com.example.intranet.interfaces.controllers.request.CreatePostRequest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/posts", produces = "application/json")
public final class PostsController {
  private final CreatePostUseCase createPostUseCase;
  private final GetPostUseCase getPostUseCase;

  @PostMapping(consumes = "application/json")
  public Post createPost(@RequestBody CreatePostRequest request) {
    return createPostUseCase.createPost(new CreatePostInput(request.content(), getUserId()));
  }

  @GetMapping
  public PagedListOutput<Post> getPosts(
      @RequestParam(value = "authorId", required = true) String authorId,
      @RequestParam(value = "startedAt", required = false) Instant startedAt,
      @RequestParam(value = "endedAt", required = false) Instant endedAt,
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "size", required = false) Integer size) {
    return getPostUseCase.getPosts(new ListUserPostInput(authorId, startedAt, endedAt, page, size));
  }

  @GetMapping("/{id}")
  public Post getPostById(@PathVariable String id) throws RecordNotFoundException {
    return getPostUseCase.getPostById(id);
  }

  private String getUserId() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication.getPrincipal().toString();
  }
}
