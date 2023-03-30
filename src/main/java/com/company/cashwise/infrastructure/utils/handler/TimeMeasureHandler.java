package com.company.cashwise.infrastructure.utils.handler;

import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Aspect
@Component
@Log
public class TimeMeasureHandler {
    @Around("com.company.cashwise.infrastructure.utils.handler.TimeMeasurePointcut.execPointcut()")
    public Object exec(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        log.info(MessageFormat.format("{0} {1} took {2} ms", proceedingJoinPoint.getSignature().getDeclaringType(),
                proceedingJoinPoint.getSignature().getName(), System.currentTimeMillis() - begin));
        return result;
    }
}

