package com.advance.user.service;

import com.advance.common.entity.User;
import com.advance.common.utils.JwtUtil;
import com.advance.user.model.dto.UserDto;
import com.advance.user.model.request.LoginRequest;
import com.advance.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void updateUserEmail(String username, String email) {
        User user = userRepository.findUserByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        user.updateEmail(email);
        userRepository.save(user);

        // AfterThrowing 실습 -> 예외 발생
        // throw new IllegalArgumentException("예외 발생");

        log.info("5번째: 서비스 레이어 메서드 실행 완료");
    }

    @Transactional
    public UserDto getUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        return UserDto.from(user);
    }

    @Transactional
    public UserDto updateUserEmailWithJpql(String username, String email) {
        User user = userRepository.findUserByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        userRepository.updateUserEmailWithJpql(username, email);
        user.updateEmail(email);
        return UserDto.from(user);
    }

    @Transactional
    public void deleteUserByUsernameWithJpql(String username) {
        userRepository.deleteUserByUsernameWithJpql(username);
    }
}

