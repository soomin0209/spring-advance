package com.advance.domain.user.controller;

import com.advance.domain.user.model.request.LoginRequest;
import com.advance.domain.user.model.response.LoginResponse;
import com.advance.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class LoginController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        String token = userService.login(request);

        return ResponseEntity.ok(new LoginResponse(token));
    }
}
