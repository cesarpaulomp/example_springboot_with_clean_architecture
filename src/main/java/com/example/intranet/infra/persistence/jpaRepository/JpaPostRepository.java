package com.example.intranet.infra.persistence.jpaRepository;

import java.time.Instant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.intranet.infra.persistence.jpaEntity.JpaPost;

@Repository
public interface JpaPostRepository extends JpaRepository<JpaPost, String> {
    // Alternativa: com GreaterThanEqual e LessThanEqual (mais flexível para nulls)
    Page<JpaPost> findAllByAuthorIdAndCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(
        String authorId,
        Instant startDate,
        Instant endDate,
        Pageable pageable
    );
}
