package com.advance.domain.post.service;

import com.advance.common.entity.Post;
import com.advance.common.entity.User;
import com.advance.domain.post.model.dto.PostDto;
import com.advance.domain.post.model.dto.PostSummaryDto;
import com.advance.domain.post.repository.PostRepository;
import com.advance.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostDto createPost(String username, String content) {
        User user = userRepository.findUserByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        Post post = new Post(content, user);
        postRepository.save(post);
        return PostDto.from(post);
    }

    public List<PostDto> getPostListByUsername(String username) {
        User user = userRepository.findUserByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        List<Post> postList = user.getPosts();
        return postList.stream()
                .map(PostDto::from)
                .collect(Collectors.toList());
    }

    public List<PostSummaryDto> getPostSummaryListByUsername(String username) {
        User user = userRepository.findUserByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

//        List<PostSummaryDto> result = new ArrayList<>();
//        for(Post post : user.getPosts()) {
//            int commentCount = post.getComments().size();
//            result.add(new PostSummaryDto(post.getContent(), commentCount));
//        }
//
//        return result;

//        // Fetch Join 사용
//        List<Post> posts = postRepository.findAllWithCommentsByUsername(username);
//        return posts.stream()
//                .map(post -> new PostSummaryDto(post.getContent(), post.getComments().size()))
//                .toList();

        List<Post> postList = postRepository.findByUserUsername(username);
        return postList.stream()
                .map(post -> new PostSummaryDto(post.getContent(), post.getComments().size()))
                .toList();
    }
}
