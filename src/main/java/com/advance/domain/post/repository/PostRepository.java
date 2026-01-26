package com.advance.domain.post.repository;

import com.advance.common.entity.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p JOIN FETCH p.comments WHERE p.user.username =:username")
    List<Post> findAllWithCommentsByUsername(@Param("username") String username);

    @EntityGraph(attributePaths = {"user", "comments"})
    List<Post> findByUserUsername(String username);
}
