package com.example.intranet.application.ports.out;

import com.example.intranet.application.ports.in.input.ListUserPostInput;
import com.example.intranet.application.ports.out.output.PagedListOutput;
import com.example.intranet.application.usecase.exception.RecordNotFoundException;
import com.example.intranet.domain.model.Post;

public interface PostRepository {
    Post createPost(Post post);
    PagedListOutput<Post> listUserPosts(ListUserPostInput command, String orderBy);
    Post getPostById(String id) throws RecordNotFoundException;
}
