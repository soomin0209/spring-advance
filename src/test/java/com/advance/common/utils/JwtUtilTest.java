package com.advance.common.utils;

import com.advance.common.enums.UserRoleEnum;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private JwtUtil jwtUtil;
    private static final String SECRET_KEY = "mysecretkeyfortestcodepracticemysecretkeyfortestcodepractice";

    // JwtUtil 초기화
    @BeforeEach
    void setup() {
        jwtUtil = new JwtUtil();
        // 객체의 값을 인위적으로 세팅해줌
        ReflectionTestUtils.setField(jwtUtil, "secretKeyString", SECRET_KEY);
        jwtUtil.init();
    }

    // JWT 토큰 생성
    @Test
    @DisplayName("generateToken_토큰생성_username과role정보포함")
    void generateToken_정상_케이스() {
        // Given
        String username = "김수민";
        UserRoleEnum role = UserRoleEnum.ADMIN;

        // When
        String jwtToken = jwtUtil.generateToken(username, role);
        String jwt = jwtToken.substring(JwtUtil.BEARER_PREFIX.length());

        // Then
        // 1. jwtToken 시작이 'Bearer'인지 검증
        assertThat(jwtToken).startsWith("Bearer ");
        // 2. jwt가 유효한지 검증
        JwtParser parser = (JwtParser) ReflectionTestUtils.getField(jwtUtil, "parser");
        assert parser != null;
        Claims claims = parser.parseSignedClaims(jwt).getPayload();

        assertThat(claims.get("username", String.class)).isEqualTo(username);
        assertThat(claims.get("auth", String.class)).isEqualTo(role.name());
    }

    // JWT 토큰 검사
    @Test
    @DisplayName("validateToken_토큰검증_유효하면true반환")
    void validateToken_성공_케이스() {
        // Given
        String username = "김수민";
        UserRoleEnum role = UserRoleEnum.ADMIN;
        String token = jwtUtil.generateToken(username, role)
                .substring(JwtUtil.BEARER_PREFIX.length());

        // When
        boolean result = jwtUtil.validateToken(token);

        // Then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("validateToken_토큰검증_유효하지않으면false반환")
    void validateToken_실패_케이스_1() {
        // Given
        String token = "thisiswrongtoken";

        // When
        boolean result = jwtUtil.validateToken(token);

        // Then
        assertThat(result).isFalse();
    }
}