package com.advance.domain.comment.service;

import com.advance.common.entity.Comment;
import com.advance.common.entity.Post;
import com.advance.domain.comment.model.dto.CommentDto;
import com.advance.domain.comment.repository.CommentRepository;
import com.advance.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentDto createComment(long postId, String content) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("등록된 Post가 없습니다.")
        );

        Comment comment = new Comment(content, post);
        commentRepository.save(comment);
        return CommentDto.from(comment);
    }
}
