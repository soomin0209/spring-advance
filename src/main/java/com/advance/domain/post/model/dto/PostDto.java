package com.advance.domain.post.model.dto;

import com.advance.common.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private long id;
    private String content;
    private String username;

    public static PostDto from(Post post) {
        return new PostDto(post.getId(), post.getContent(), post.getUser().getUsername());
    }
}
