package com.advance.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class TimeCheckAop {
    // 어떤것을 : UserService 하위의 메서드
    // 언제 : 메서드 실행 전후
    // 어떻게 : executionTime 메서드 안의 내용을 실행

//    // 메서드 실행 전후로 시간을 비교 분석
//    @Around("execution(* com.advance.user.service.UserService.*(..))")
//    public Object executionTime(ProceedingJoinPoint joinPoint) throws Throwable {
//
//        // 메서드 실행 전
//        long start = System.currentTimeMillis();
//        log.info("4번째: 서비스 레이어 메서드 실행 전 AOP 로직 수행");
//
//        Object result = joinPoint.proceed();    // 실제 메서드 실행 -> Filter의 doFilter
//
//        // 메서드 실행 후
//        long end = System.currentTimeMillis();
//        log.info("6번째: 서비스 레이어 메서드 실행 후 AOP 로직 수행");
//
//        log.info("[AOP] {} 실행됨 in {} ms", joinPoint.getSignature(), end - start);
//        return result;
//    }

    // 로그인 인증/인가 -> Filter
    // user email 수정 -> Interceptor에서 수행
    // userEmailUpdate 메서드 -> AOP에서 수행

    // 요청 -> JwtFilter 통과 -> OwnerCheckInterceptor -> UserController 접근
    // -> UserService의 UserEmailUpdate -> 타겟 대상인 서비스 레이어의 메서드 실행 전 -> TimeCheckAop '실행 전 로직' 수행
    // -> userEmailUpdate 메서드 실행 끝 -> TimeCheckAop '실행 후 로직' 수행
    // -> Controller에서 return 값 리턴 -> JwtFilter 통과 -> postman으로 결과 전달

    @Before("execution(* com.advance.user.service.UserService.*(..))")
    public void executionTime() throws Throwable {

        log.info("메서드 실행 전에만 수행");
    }
}
