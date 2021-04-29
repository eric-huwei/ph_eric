package com.eric.config;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @DESCIRPTION JDBC AOP
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/4/29 上午10:31
 */
@Aspect
@Component
public class JdbcExceptionAop {

    @Around("execution(* com.eric.mybatis.mapper..*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }
}
