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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/posts", produces = "application/json")
@Tag(name = "Posts", description = "Endpoints for post management")
@SecurityRequirement(name = "bearerAuth")
public final class PostsController {
  private final CreatePostUseCase createPostUseCase;
  private final GetPostUseCase getPostUseCase;

  @Operation(
    summary = "Create new post",
    description = "Creates a new post associated with the authenticated user"
  )
  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "200",
      description = "Post created successfully",
      content = @Content(schema = @Schema(implementation = Post.class))
    ),
    @ApiResponse(
      responseCode = "401",
      description = "User not authenticated",
      content = @Content
    ),
    @ApiResponse(
      responseCode = "400",
      description = "Invalid data provided",
      content = @Content
    )
  })
  @PostMapping(consumes = "application/json")
  public Post createPost(@RequestBody CreatePostRequest request) {
    return createPostUseCase.createPost(new CreatePostInput(request.content(), getUserId()));
  }

  @Operation(
    summary = "List posts by author",
    description = "Returns a paginated list of posts from a specific author, with optional date filters"
  )
  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "200",
      description = "Post list returned successfully",
      content = @Content(schema = @Schema(implementation = PagedListOutput.class))
    ),
    @ApiResponse(
      responseCode = "401",
      description = "User not authenticated",
      content = @Content
    )
  })
  @GetMapping
  public PagedListOutput<Post> getPosts(
      @Parameter(description = "ID of the post author", required = true)
      @RequestParam(value = "authorId", required = true) String authorId,
      @Parameter(description = "Filter start date (ISO-8601)", example = "2024-01-01T00:00:00Z")
      @RequestParam(value = "startedAt", required = false) Instant startedAt,
      @Parameter(description = "Filter end date (ISO-8601)", example = "2024-12-31T23:59:59Z")
      @RequestParam(value = "endedAt", required = false) Instant endedAt,
      @Parameter(description = "Page number (starts at 0)", example = "0")
      @RequestParam(value = "page", required = false) Integer page,
      @Parameter(description = "Page size", example = "10")
      @RequestParam(value = "size", required = false) Integer size) {
    return getPostUseCase.getPosts(new ListUserPostInput(authorId, startedAt, endedAt, page, size));
  }

  @Operation(
    summary = "Get post by ID",
    description = "Returns a specific post by its ID"
  )
  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "200",
      description = "Post found successfully",
      content = @Content(schema = @Schema(implementation = Post.class))
    ),
    @ApiResponse(
      responseCode = "404",
      description = "Post not found",
      content = @Content
    ),
    @ApiResponse(
      responseCode = "401",
      description = "User not authenticated",
      content = @Content
    )
  })
  @GetMapping("/{id}")
  public Post getPostById(
      @Parameter(description = "Post ID", required = true)
      @PathVariable String id) throws RecordNotFoundException {
    return getPostUseCase.getPostById(id);
  }

  private String getUserId() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication.getPrincipal().toString();
  }
}
