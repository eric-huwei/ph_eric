package com.eric.concurrent;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/12/2 4:41 PM
 */
public class DaemonThread implements Runnable {

    @Override
    public void run() {
        System.out.println("守护线程开始");
    }

}

class ThreadEx {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*Thread a = new Thread(new DaemonThread());
        a.setDaemon(true);
        a.start();*/
        Future result = Executors.newFixedThreadPool(10).submit(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(new Random(10).nextInt());
        });
        System.out.println("get start");
        try {
            result.get(1000, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            System.out.println("获取超时，进行其他业务操作");
        }
        System.out.println("get end");
    }
}