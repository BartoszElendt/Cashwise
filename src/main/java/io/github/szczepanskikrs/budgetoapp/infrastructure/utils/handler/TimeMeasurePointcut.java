package io.github.szczepanskikrs.budgetoapp.infrastructure.utils.handler;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeMeasurePointcut {
    @Pointcut("@annotation(io.github.szczepanskikrs.budgetoapp.infrastructure.utils.TimeMeasure)")
    private void execAll() {}

    @Pointcut("execAll()")
    public void execPointcut() {}
}