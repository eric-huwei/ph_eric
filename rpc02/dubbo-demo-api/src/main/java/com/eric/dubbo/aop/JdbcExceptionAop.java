package com.eric.dubbo.aop;


/*import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

*//**
 * @DESCIRPTION JDBC AOP
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/4/29 上午10:31
 *//*
@Aspect
@Component
public class JdbcExceptionAop {

    @Pointcut("execution(* com.eric.mybatis.mapper..*.*(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void before() {
        Logger.getGlobal().info("point cut before: " + System.currentTimeMillis());
    }

    @After("pointCut()")
    public void after() {
        Logger.getGlobal().info("point cut after: " + System.currentTimeMillis());
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object result = null;
        try {
            long begin = System.currentTimeMillis();
            result = joinPoint.proceed();
            long end = System.currentTimeMillis();

            Logger.getGlobal().info("当前sqlId:"+joinPoint.getSignature().toShortString()+
                    ",参数:"+ Arrays.toString(joinPoint.getArgs()) +",执行耗时:"+(end - begin)+"ms");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }
}*/
