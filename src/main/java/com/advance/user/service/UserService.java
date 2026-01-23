package com.advance.user.service;

import com.advance.common.entity.User;
import com.advance.common.utils.JwtUtil;
import com.advance.user.model.request.LoginRequest;
import com.advance.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public User save(User user) {
        return userRepository.save(user);
    }

    public String login(LoginRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();

        User user = userRepository.findUserByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return jwtUtil.generateToken(user.getUsername(), user.getRoleEnum());
    }
}

