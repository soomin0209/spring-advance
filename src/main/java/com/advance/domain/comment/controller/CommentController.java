package com.advance.domain.comment.controller;

import com.advance.domain.comment.model.dto.CommentDto;
import com.advance.domain.comment.model.request.CreateCommentRequest;
import com.advance.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postId}")
    public ResponseEntity<CommentDto> createComment(@PathVariable long postId, @RequestBody CreateCommentRequest request) {
        return ResponseEntity.ok(commentService.createComment(postId, request.getContent()));
    }
}
