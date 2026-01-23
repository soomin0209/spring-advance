package com.advance.user.controller;

import com.advance.common.utils.JwtUtil;
import com.advance.user.model.request.LoginRequest;
import com.advance.user.model.request.UpdateUserEmailRequest;
import com.advance.user.model.response.LoginResponse;
import com.advance.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    private final JwtUtil jwtUtil;
    private final UserService userService;

    @PreAuthorize("hasRole('ADMIN')") // -> SecurityConfig @EnableMethodSecurity 활성화
    @GetMapping("/get")
    public String getUserInfo(@AuthenticationPrincipal User user) {
        log.info(user.getUsername());
        return user.getUsername();
    }

//    @PostMapping("/login")
//    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
//
//        String token  = jwtUtil.generateToken(request.getUsername());
//
//        return ResponseEntity.ok(new LoginResponse(token));
//    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> checkValidate(HttpServletRequest request) {

        String authorizationHeader = request.getHeader("Authorization");

        String jwt = authorizationHeader.substring(7);

        Boolean validate = jwtUtil.validateToken(jwt);

        return ResponseEntity.ok(validate);
    }

    @GetMapping("/username")
    public ResponseEntity<String> getUsername(HttpServletRequest request) {

        String authorizationHeader = request.getHeader("Authorization");

        String jwt = authorizationHeader.substring(7);

        String username = jwtUtil.extractUsername(jwt);

        return ResponseEntity.ok(username);
    }

    @PutMapping("/{username}/email")
    public ResponseEntity<String> updateEmail(@PathVariable String username, @RequestBody UpdateUserEmailRequest request) {
        log.info("3번째: Interceptor를 통과 후 Controller 로직 수행");
        userService.updateUserEmail(username, request.getEmail());

        log.info("7번째: Controller 수행 완료");
        return ResponseEntity.ok("수정 완료");
    }

    // 이메일 수정은 본인만 가능
}