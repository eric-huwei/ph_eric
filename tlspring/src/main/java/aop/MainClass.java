package aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2022/3/16 1:32 PM
 */
public class MainClass {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
        Calculate calculate = (Calculate) ctx.getBean("tlCalculate");
        calculate.add(1,3);
    }
}
