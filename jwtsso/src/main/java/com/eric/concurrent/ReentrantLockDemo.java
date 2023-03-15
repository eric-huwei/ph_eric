package com.eric.concurrent;

import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @DESCIRPTION 独占锁、互斥锁
 * @AUTHOR SCORPIO.HU
 * @DATE 2023/2/9 11:24 AM
 */
public class ReentrantLockDemo implements BeanPostProcessor {

    private static Lock lock = new ReentrantLock();

    private static  int count = 0;

    /**
     * 重入锁 一个持有锁的线程，在释放锁之前。此线程如果再次访问了该同步锁的其他的方法，这个线程不需要再次竞争锁，只需要记录重入次数。重入锁的设计目的是为了解决死锁的问题
     *
     * inr() 方法获取锁成功并没有释放锁的情况下调用dec()再次获取锁，假如没有重入锁的话这里会导致死锁。
     */
    private static void inrc() {
        try {
            //加锁
            lock.lock();
            Thread.sleep(10);
            count++;
            //模拟重入锁
            dec();//2
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    private static void dec(){
        lock.lock();
        count--;
        lock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
        /*for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                inrc();//1
            }).start();
        }
        TimeUnit.SECONDS.sleep(3);
        System.out.println(count);*/

        AtomicInteger atomicInteger = new AtomicInteger(5);
        boolean success = atomicInteger.compareAndSet(5, 10);
        System.out.println(success);
        System.out.println(atomicInteger.get());
    }

}
