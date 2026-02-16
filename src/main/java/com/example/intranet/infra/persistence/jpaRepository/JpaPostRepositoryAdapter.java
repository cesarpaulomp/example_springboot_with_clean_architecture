package com.example.intranet.infra.persistence.jpaRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.example.intranet.application.ports.in.input.ListUserPostInput;
import com.example.intranet.application.ports.out.PostRepository;
import com.example.intranet.application.ports.out.output.PagedListOutput;
import com.example.intranet.application.usecase.exception.RecordNotFoundException;
import com.example.intranet.domain.model.Post;
import com.example.intranet.infra.mapper.PostMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JpaPostRepositoryAdapter implements PostRepository {

    private final PostMapper postMapper;
    private final JpaPostRepository jpaPostRepository;

    @Override
    public Post createPost(Post post) {
        var jpaPost = postMapper.toJpaPost(post);
        var savedJpaPost = jpaPostRepository.save(jpaPost);
        return postMapper.toDomain(savedJpaPost);
    }

    @Override
    public PagedListOutput<Post> listUserPosts(ListUserPostInput input, String orderBy) {
        // Cria o Pageable com ordenação
        Sort sort = Sort.by(Sort.Direction.DESC, orderBy != null ? orderBy : "createdAt");
        Pageable pageable = PageRequest.of(input.getPage(), input.getSize(), sort);
        var page = jpaPostRepository.findAllByAuthorIdAndCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(
            input.authorId(),
            input.getStartedAt(),
            input.getEndedAt(),
            pageable
        );
        
        // Converte Page<JpaPost> para PagedListOutput<Post>
        var posts = page.getContent().stream()
            .map(postMapper::toDomain)
            .toList();
            
        return new PagedListOutput<>(
            posts,
            page.getNumber(),
            page.getSize(),
            page.getTotalElements()
        );
    }

    @Override
    public Post getPostById(String id) throws RecordNotFoundException {
        var jpaPost = jpaPostRepository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException());
        return postMapper.toDomain(jpaPost);
    }
    
}
