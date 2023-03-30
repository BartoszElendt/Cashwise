package com.company.cashwise.infrastructure.utils.handler;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeMeasurePointcut {
    @Pointcut("@annotation(com.company.cashwise.infrastructure.utils.TimeMeasure)")
    private void execAll() {}

    @Pointcut("execAll()")
    public void execPointcut() {}
}