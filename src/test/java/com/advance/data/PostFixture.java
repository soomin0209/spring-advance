package com.advance.data;

import com.advance.common.entity.Post;
import org.springframework.test.util.ReflectionTestUtils;

public class PostFixture {

    public static String DEFAULT_CONTENT = "테스트 게시글";

    public static Post createPost1() {
        Post post = new Post(DEFAULT_CONTENT, UserFixture.createUserAdminRole());
        ReflectionTestUtils.setField(post, "id", 1L);
        return post;
    }

    public static Post createPost2() {
        Post post = new Post(DEFAULT_CONTENT, UserFixture.createUserAdminRole());
        ReflectionTestUtils.setField(post, "id", 2L);
        return post;
    }
}
