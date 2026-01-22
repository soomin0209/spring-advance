package com.advance.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    @GetMapping("/get")
    public String getUserInfo() {
        log.info("호출");
        return "호출됨";
    }
}