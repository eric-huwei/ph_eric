package com.eric.java8;

/**
 * @DESCIRPTION lambda demo
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/5/20 下午2:19
 */
public class LambdaDemo {


    public static void main(String[] args) {
        MathOperation add1 = new MathOperation() {
            @Override
            public Object operation(int a, int b) {
                return a + b;
            }
        };

        MathOperation add2 = (int a, int b) -> a + b;

        System.out.println(add1.operation(1,2));
        System.out.println(add2.operation(1,2));

        System.out.println(operate(1, 2, add1));

        MessagePrint msg1 = new MessagePrint() {
            @Override
            public void sayMessage(String msg) {
                System.out.println(msg);
            }
        };

        MessagePrint msg2 = (String msg) -> System.out.println(msg);

        msg1.sayMessage("hello world!");
        msg2.sayMessage("hello world!");
    }

    interface MessagePrint {
        void sayMessage(String msg);
    }

    interface MathOperation<T> {
        T operation(int a, int b);
    }

    private static  <T> T operate(int a, int b, MathOperation<T> mathOperation) {
        return mathOperation.operation(a, b);
    }
}
