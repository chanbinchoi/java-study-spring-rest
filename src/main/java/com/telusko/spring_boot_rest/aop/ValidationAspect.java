package com.telusko.spring_boot_rest.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidationAspect {

    // 로그 출력 도구 객체
    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationAspect.class);

    // getJob 메서드 가로채기 및 인자(postId) 추출
    @Around("execution(* com.telusko.spring_boot_rest.service.JobService.getJob(..)) && args(postId)")
    public Object validateAndUpdate(ProceedingJoinPoint jp, int postId) throws Throwable {

        // 입력값이 음수일 경우 처리 로직
        if (postId < 0) {
            LOGGER.info("PostId is negative, updating it");
            // 음수를 양수로 강제 변환
            postId = -postId;
            LOGGER.info("new value " + postId);
        }

        // [중요] 수정된 인자(postId)를 담아 실제 메서드 실행
        Object obj = jp.proceed(new Object[]{postId});

        return obj;
    }
}