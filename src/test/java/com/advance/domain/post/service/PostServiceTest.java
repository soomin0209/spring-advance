package com.advance.domain.post.service;

import com.advance.common.entity.Post;
import com.advance.common.entity.User;
import com.advance.common.enums.UserRoleEnum;
import com.advance.domain.post.model.dto.PostDto;
import com.advance.domain.post.repository.PostRepository;
import com.advance.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    // 가짜 객체
    @Mock
    private PostRepository postRepository;
    @Mock
    private UserRepository userRepository;

    // 가짜 객체 주입
    @InjectMocks
    private PostService postService;

    // static 변수 활용
    public static String DEFAULT_USERNAME = "김수민";

    // 게시글 생성
    @Test
    @DisplayName("createPost_게시글생성_성공")
    void createPost_성공_케이스() {
        // Given
        String content = "테스트 게시글";
        User testUser = new User(DEFAULT_USERNAME, "1234", "test@test.com", UserRoleEnum.ADMIN);
        Post testPost = new Post(content, testUser);
        ReflectionTestUtils.setField(testPost, "id", 1L);

        when(userRepository.findUserByUsername(DEFAULT_USERNAME)).thenReturn(Optional.of(testUser));
        when(postRepository.save(any(Post.class))).thenReturn(testPost);

        // When
        PostDto result = postService.createPost(DEFAULT_USERNAME, content);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(testPost.getId());
        assertThat(result.getUsername()).isEqualTo(DEFAULT_USERNAME);
        assertThat(result.getContent()).isEqualTo(content);
    }

    @Test
    @DisplayName("getPostListByUsername_게시글조회_성공")
    void getPostListByUsername_성공_케이스() {
        // Given
        User testUser = new User(DEFAULT_USERNAME, "1234", "test@test.com", UserRoleEnum.ADMIN);
        List<Post> postList = List.of(
                new Post("테스트 게시글 1", testUser),
                new Post("테스트 게시글 2", testUser)
        );
        ReflectionTestUtils.setField(postList.get(0), "id", 1L);
        ReflectionTestUtils.setField(postList.get(1), "id", 2L);

        testUser.getPosts().addAll(postList);
        when(userRepository.findUserByUsername(DEFAULT_USERNAME)).thenReturn(Optional.of(testUser));

        // When
        List<PostDto> result = postService.getPostListByUsername(DEFAULT_USERNAME);

        // Then
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getUsername()).isEqualTo(DEFAULT_USERNAME);
        assertThat(result.get(0).getContent()).isEqualTo("테스트 게시글 1");
        assertThat(result.get(1).getUsername()).isEqualTo(DEFAULT_USERNAME);
        assertThat(result.get(1).getContent()).isEqualTo("테스트 게시글 2");
    }

    @Test
    @DisplayName("getPostListByUsername_게시글조회_실패")
    void getPostListByUsername_실패_케이스() {
        // Given
        when(userRepository.findUserByUsername("김수민")).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> postService.getPostListByUsername("김수민"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("등록된 사용자가 없습니다.");
    }
}