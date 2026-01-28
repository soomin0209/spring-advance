//package com.advance;
//
//import com.advance.common.entity.Comment;
//import com.advance.common.entity.Post;
//import com.advance.common.entity.User;
//import com.advance.common.enums.UserRoleEnum;
//import com.advance.domain.comment.repository.CommentRepository;
//import com.advance.domain.post.repository.PostRepository;
//import com.advance.domain.user.repository.UserRepository;
//import com.advance.domain.user.service.UserService;
//import jakarta.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//public class InitData {
//
//    private final PasswordEncoder passwordEncoder;
//    private final UserService userService;
//    private final UserRepository userRepository;
//    private final PostRepository postRepository;
//    private final CommentRepository commentRepository;
//
//    @PostConstruct
//    @Transactional
//    public void init() {
//        User user1 = new User("김수민", passwordEncoder.encode("1234"), "user1@example.com", UserRoleEnum.ADMIN);
//        User user2 = new User("장은성", passwordEncoder.encode("1234"), "user2@example.com", UserRoleEnum.NORMAL);
//
//        userRepository.save(user1);
//        userRepository.save(user2);
//
//        Post post1 = new Post("1번 게시글", user1);
//        Post post2 = new Post("2번 게시글", user1);
//        Post post3 = new Post("3번 게시글", user2);
//
//        postRepository.save(post1);
//        postRepository.save(post2);
//        postRepository.save(post3);
//
//        Comment comment1 = new Comment("1번 댓글", post1);
//        Comment comment2 = new Comment("2번 댓글", post1);
//        Comment comment3 = new Comment("3번 댓글", post2);
//        Comment comment4 = new Comment("4번 댓글", post2);
//        Comment comment5 = new Comment("5번 댓글", post3);
//        Comment comment6 = new Comment("6번 댓글", post3);
//        Comment comment7 = new Comment("7번 댓글", post3);
//
//        commentRepository.save(comment1);
//        commentRepository.save(comment2);
//        commentRepository.save(comment3);
//        commentRepository.save(comment4);
//        commentRepository.save(comment5);
//        commentRepository.save(comment6);
//        commentRepository.save(comment7);
//
//    }
//}
