package com.eric.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @DESCIRPTION 信号量
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/1/30 下午5:47
 */
public class SemaphoreDemo {

    private final static int THREAD_COUNT = 20;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(5);

        for (int i = 0; i < THREAD_COUNT; i++) {
            int finalI = i;
            executorService.execute(()-> {
                try {
                    System.out.println("第" + finalI + "个线程来了");
                    semaphore.acquire();
                    System.out.println("第" + finalI + "个线程，开始执行======");
                    Thread.sleep(3000);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }
}
