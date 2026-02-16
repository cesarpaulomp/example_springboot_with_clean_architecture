package com.example.intranet.infra.mapper;

import org.springframework.stereotype.Component;

import com.example.intranet.domain.model.Post;
import com.example.intranet.infra.persistence.jpaEntity.JpaPost;

@Component
public class PostMapper {
    public JpaPost toJpaPost(Post post) {
        if (post == null) {
            return null;
        }
        JpaPost jpaPost = new JpaPost();
        jpaPost.setId(post.id());
        jpaPost.setContent(post.content());
        jpaPost.setAuthorId(post.authorId());
        jpaPost.setCreatedAt(post.createdAt());
        jpaPost.setUpdatedAt(post.updatedAt());
        return jpaPost;
    }

    public Post toDomain(JpaPost jpaPost) {
        if (jpaPost == null) {
            return null;
        }
        return new Post(
            jpaPost.getId(),
            jpaPost.getContent(),
            jpaPost.getAuthorId(),
            jpaPost.getCreatedAt(),
            jpaPost.getUpdatedAt()
        );
    }
}
