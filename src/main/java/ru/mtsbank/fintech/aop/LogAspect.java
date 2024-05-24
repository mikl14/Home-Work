package ru.mtsbank.fintech.aop;


import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import ru.mtsbank.fintech.annotations.Logging;

@Aspect
@Log4j2
@Component
public class LogAspect {

    private static final Logger logger = LogManager.getLogger(LogAspect.class);

    @Before("@annotation(logging)")
    public void logMethodCall(JoinPoint joinPoint, Logging logging) {
        if (logging.entering()) {
            if (!logging.value().isEmpty()) {
                logger.log(Level.toLevel(logging.level()), ">> " + logging.value());
            } else {
                logger.log(Level.toLevel(logging.level()), ">> " + joinPoint.getSignature().getName());
            }
        }
    }

    @After("@annotation(logging)")
    public void logMethodExit(JoinPoint joinPoint, Logging logging) {
        if (logging.exiting()) {
            if (!logging.value().isEmpty()) {
                logger.log(Level.toLevel(logging.level()), "<< " + logging.value());
            } else {
                logger.log(Level.toLevel(logging.level()), "<< " + joinPoint.getSignature().getName());
            }
        }
    }

    @Before("execution(* ru.mtsbank.fintech.controller.UIAnimalController.*(..))")
    public void logMethodCall(JoinPoint joinPoint) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getMethod().getName();
        Object[] args = joinPoint.getArgs();

        logger.info(">> {} with args: {}", methodName, args);
    }
}
