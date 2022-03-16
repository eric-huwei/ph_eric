package aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2022/3/16 1:19 PM
 */
@Aspect
@Component
public class TLAspect {

    @Pointcut("execution(* aop.TlCalculate.*(..))")
    public void pointCut() {

    }

    @Before(value = "pointCut()")
    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("执行目标方法『" + methodName + "』<前置通知>，入参"+ joinPoint.getArgs());
    }

    @After(value = "pointCut()")
    public void afterMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("执行目标方法『" + methodName + "』<后置通知>，入参"+ joinPoint.getArgs());
    }
}
