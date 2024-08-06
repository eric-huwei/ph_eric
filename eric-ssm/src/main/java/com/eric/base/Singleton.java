package com.eric.base;

/**
 * @DESCIRPTION 单例模式-双重校验锁方式
 * @AUTHOR SCORPIO.HU
 * @DATE 2022/2/9 10:17 PM
 */
public class Singleton {
    private volatile static Singleton uniqueInstance;

    private Singleton() {

    }

    public static Singleton getUniqueInstance() {
        if (null == uniqueInstance) {
            synchronized (Singleton.class) {
                if (null == uniqueInstance) {
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }
}
