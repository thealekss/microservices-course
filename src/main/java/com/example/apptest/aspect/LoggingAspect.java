package com.example.apptest.aspect;

import com.example.apptest.model.Greeting;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

@Aspect
@Component
public class LoggingAspect {

    private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @AfterReturning(pointcut = "execution(* com.example.apptest.controller.GreetingController.*(..))", returning = "result")
    void logIncomeOutcomeParams(JoinPoint joinPoint, Object result) {
        logger.info("Вызван метод: {}, входящие параметры: {}, исходящие параметры: {}", joinPoint.getSignature().getName(),
                joinPoint.getArgs(), result.toString());
    }
}
