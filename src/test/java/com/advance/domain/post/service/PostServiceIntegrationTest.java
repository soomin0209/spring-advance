package com.advance.domain.post.service;

import com.advance.common.entity.Post;
import com.advance.common.entity.User;
import com.advance.data.UserFixture;
import com.advance.domain.post.model.dto.PostDto;
import com.advance.domain.post.repository.PostRepository;
import com.advance.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostServiceIntegrationTest {

    @Autowired
    private PostService postService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Test
    @DisplayName("게시글 생성 통합 테스트 -  실제 DB에 저장 및 조회 검증")
    void createPost_통합테스트_success() {
        // Given
        User user = UserFixture.createUserAdminRole();
        userRepository.save(user);

        // When
        PostDto result = postService.createPost(user.getUsername(), "테스트 게시글 입니다.");

        // Then
        List<Post> savePostList = postRepository.findAll();

        assertThat(savePostList).hasSize(1);
        assertThat(savePostList.get(0).getUser().getUsername()).isEqualTo(user.getUsername());
        assertThat(savePostList.get(0).getContent()).isEqualTo("테스트 게시글 입니다.");
    }
}