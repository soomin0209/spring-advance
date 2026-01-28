package com.advance.domain.post.controller;

import com.advance.common.entity.User;
import com.advance.common.utils.JwtUtil;
import com.advance.data.UserFixture;
import com.advance.domain.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static com.advance.data.UserFixture.DEFAULT_USERNAME;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    private String token;

    @BeforeEach
    void setUp() {

        User user = UserFixture.createUserAdminRole();
        userRepository.save(user);
        token = jwtUtil.generateToken(user.getUsername(), user.getRoleEnum());
    }

    @Test
    @DisplayName("POST /api/post - 게시글 생성 요청 성공")
    void createPost_통합테스트_success() throws Exception {
        String requestBody =
                """
                    {
                        "content": "통합 테스트 게시글입니다."
                    }
                """;

        mockMvc.perform(post("/api/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("통합 테스트 게시글입니다."))
                .andExpect(jsonPath("$.username").value(DEFAULT_USERNAME));
    }
}