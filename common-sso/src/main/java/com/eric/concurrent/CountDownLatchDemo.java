package com.eric.concurrent;

import lombok.SneakyThrows;

import java.util.concurrent.*;

/**
 * @DESCIRPTION 计数器
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/1/31 下午2:24
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,10, 0L,
                TimeUnit.SECONDS, new ArrayBlockingQueue(10));
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 10; i++) {
            executor.submit(new ReadNum(countDownLatch));
        }
    }
}

class ReadNum implements Runnable {

    private CountDownLatch downLatch;

    public ReadNum(CountDownLatch countDownLatch) {
        this.downLatch = countDownLatch;
    }

    @SneakyThrows
    @Override
    public void run() {
        Thread.sleep(10000);
        System.out.println("当前子线程" + Thread.currentThread().getName() + "==> 执行完成");
        downLatch.countDown();
    }
}