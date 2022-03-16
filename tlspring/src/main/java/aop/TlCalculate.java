package aop;

import org.springframework.stereotype.Service;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2022/3/16 10:47 AM
 */
@Service("tlCalculate")
public class TlCalculate implements Calculate {
    @Override
    public int add(int numA, int numB) {
        System.out.println("执行目标+加+方法");
        return numA + numB;
    }

    @Override
    public int sub(int numA, int numB) {
        System.out.println("执行目标-减-方法");
        return numA - numB;
    }
}
