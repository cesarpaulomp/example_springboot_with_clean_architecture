package com.example.intranet.application.ports.in;

import java.util.List;

import com.example.intranet.application.ports.in.input.ListUserPostInput;
import com.example.intranet.application.ports.out.output.PagedListOutput;
import com.example.intranet.application.usecase.exception.RecordNotFoundException;
import com.example.intranet.domain.model.Post;

public interface GetPostUseCase {
    Post getPostById(String id) throws RecordNotFoundException;
    PagedListOutput<Post> getPosts(ListUserPostInput command);
}
