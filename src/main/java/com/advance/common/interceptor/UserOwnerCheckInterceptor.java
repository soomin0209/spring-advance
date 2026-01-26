package com.advance.common.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class UserOwnerCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        // 1. 현재 로그인한 사용자 이름 꺼내기
        // -> JwtFilter에서 넣어준 현재 로그인 사용자 꺼내기
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = auth.getName();

        // 2. 요청한 URL(/api/user/{username}/email)에서 username 추출
        String path = request.getRequestURI();
        String decodePath = URLDecoder.decode(path, StandardCharsets.UTF_8);

        String[] parts = decodePath.split("/");
        String username = parts[parts.length - 2];

        // 3. 로그인한 사용자와 이메일 변경하려는 사용자가 일치하는지 검사

        // 4. 불일치 -> Controller 접근 거부
        if (!currentUsername.equals(username)) {
            log.info("소유자가 아닙니다. 접근 거부");
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "소유자만 수정할 수 있습니다.");
            return false;
        }

//        log.info("2번째: Interceptor에서 controller 들어가기 전 마지막 권한 검사 실행");

        // 5. 일치 -> 변경 (Controller 접근 허용)
        return true;
    }
}
