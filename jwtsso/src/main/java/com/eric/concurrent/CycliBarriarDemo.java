package com.eric.concurrent;

import java.util.concurrent.*;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/12/3 1:25 PM
 */
public class CycliBarriarDemo {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 0L,
                TimeUnit.SECONDS, new ArrayBlockingQueue(10));
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        for (int i = 0; i < 10; i++) {
            executor.submit(new Executor(cyclicBarrier));
        }
    }
}

class Executor implements Callable {

    private CyclicBarrier cyclicBarrier;
    Executor(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public Object call() throws Exception {
        cyclicBarrier.await();
        Thread.sleep(5000);
        System.out.println(Thread.currentThread().getName());
        return null;
    }
}