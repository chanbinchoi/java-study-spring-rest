package com.telusko.spring_boot_rest.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    // 로그 출력 도구 객체
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    // 타겟 메서드 실행 전 로그 기록
    @Before("execution(* com.telusko.spring_boot_rest.service.JobService.getJob(..)) || execution(* com.telusko.spring_boot_rest.service.JobService.updateJob(..))")
    public void logMethodCall(JoinPoint jp) {
        LOGGER.info("Method called " + jp.getSignature().getName());
    }

    // 결과 무관 메서드 종료 후 로그 기록
    @After("execution(* com.telusko.spring_boot_rest.service.JobService.getJob(..)) || execution(* com.telusko.spring_boot_rest.service.JobService.updateJob(..))")
    public void logMethodExecuted(JoinPoint jp) {
        LOGGER.info("Method Executed " + jp.getSignature().getName());
    }

    // 예외 발생 시 로그 기록
    @AfterThrowing("execution(* com.telusko.spring_boot_rest.service.JobService.getJob(..)) || execution(* com.telusko.spring_boot_rest.service.JobService.updateJob(..))")
    public void logMethodCrash(JoinPoint jp) {
        LOGGER.info("Method has some issues " + jp.getSignature().getName());
    }

    // 정상 종료 시 로그 기록
    @AfterReturning("execution(* com.telusko.spring_boot_rest.service.JobService.getJob(..)) || execution(* com.telusko.spring_boot_rest.service.JobService.updateJob(..))")
    public void logMethodExecutedSuccess(JoinPoint jp) {
        LOGGER.info("Method Executed Successfully " + jp.getSignature().getName());
    }
}