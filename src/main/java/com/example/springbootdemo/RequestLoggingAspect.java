package com.example.springbootdemo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Aspect
@Component
public class RequestLoggingAspect {

    @Before("execution(* com.example.springbootdemo.controller..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("📌 [AOP LOG] Time: " + new Date());
        System.out.println("📌 Method: " + joinPoint.getSignature().getName());
        System.out.println("📌 Args: " + Arrays.toString(joinPoint.getArgs()));
    }
}
