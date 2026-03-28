package com.telusko.spring_boot_rest.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerformanceMonitorAspect {

    // 로그 출력 도구 객체
    private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceMonitorAspect.class);

    // getJob 메서드 앞뒤 전체 과정 제어
    @Around("execution(* com.telusko.spring_boot_rest.service.JobService.*(..))")
    public Object monitorTime(ProceedingJoinPoint jp) throws Throwable {

        // 메서드 실행 전 시작 시간 측정
        long start = System.currentTimeMillis();

        // [중요] 실제 대상 메서드(getJob) 실행 및 결과 보관
        Object obj = jp.proceed();

        // 메서드 실행 후 종료 시간 측정
        long end = System.currentTimeMillis();

        // 결과값과 상관없이 소요 시간 로그 출력
        LOGGER.info("Time taken by : " + jp.getSignature().getName() + " : " + (end - start) + " ms");

        // 실제 메서드가 원래 돌려줘야 할 결과값 반환
        return obj;
    }
}