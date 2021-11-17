package com.eric.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @DESCIRPTION 计数器
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/1/31 下午2:24
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(new ReadNum(i, countDownLatch)).start();
        }
    }
}

class ReadNum implements Runnable {

    private int i;
    private CountDownLatch downLatch;

    public ReadNum(int i, CountDownLatch countDownLatch) {
        this.i = i;
        this.downLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println("当前子线程" + i + "==> 执行完成");
    }
}