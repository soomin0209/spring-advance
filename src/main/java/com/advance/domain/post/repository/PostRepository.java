package com.advance.domain.post.repository;

import com.advance.common.entity.Post;
import com.advance.domain.post.model.dto.PostSummaryDto;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("""
    SELECT new com.advance.domain.post.model.dto.PostSummaryDto(
        p.content,
        SIZE(p.comments) 
    )
    FROM Post p
    WHERE p.user.username =:username
    """)
    List<PostSummaryDto> findAllWithCommentsByUsername(@Param("username") String username);

    @EntityGraph(attributePaths = {"user", "comments"})
    List<Post> findByUserUsername(String username);
}

/*
@Query("""
    SELECT new org.example.nbcam_addvanced_1.domain.post.model.dto.PostSummaryDto(
        p.content,
        SIZE(p.comments)
    )
    FROM Post p
    WHERE p.user.username = :username
    """)
List<PostSummaryDto> findAllWithCommentsByUsername(String username);
*/
