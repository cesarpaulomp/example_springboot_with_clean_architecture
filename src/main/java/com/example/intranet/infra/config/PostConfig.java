package com.example.intranet.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.intranet.application.ports.in.CreatePostUseCase;
import com.example.intranet.application.ports.in.GetPostUseCase;
import com.example.intranet.application.ports.out.PostRepository;
import com.example.intranet.application.usecase.CreatePostUseCaseImpl;
import com.example.intranet.application.usecase.GetPostUseCaseImpl;

@Configuration
public class PostConfig {
    
    @Bean
    public CreatePostUseCase createPostUseCase(PostRepository postRepository) {
        return new CreatePostUseCaseImpl(postRepository);
    }
    
    @Bean
    public GetPostUseCase getPostUseCase(PostRepository postRepository) {
        return new GetPostUseCaseImpl(postRepository);
    }
}
