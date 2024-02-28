package ru.myapps.taskapp.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformerActionLoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(PerformerActionLoggingAspect.class);

    @Pointcut("@annotation(TrackPerformerAction)")
    public void trackPerformerAction() {
    }


    @Before("trackPerformerAction()")
    public void before(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] args = joinPoint.getArgs();

        logger.info("Performer action - Class: {}, Method: {}, Args: {}", className, methodName, args);
    }
}
