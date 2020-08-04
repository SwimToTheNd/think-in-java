package com.me.thinkinginjava.chapter21_concurrent.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Lock锁以Synchronize方式实现
 * create by BloodFly at 2020/3/10
 */
public class DiyLockDemo {
    private int count;
    private DiySynchronizedLock lock = new DiySynchronizedLock();

    public void incrementCount() throws Exception {
        lock.lock();
        count++;
        lock.unLock();
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        DiyLockDemo diyLockDemo = new DiyLockDemo();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10000; i++) {
            executorService.execute(() -> {
                try {
                    diyLockDemo.incrementCount();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
        TimeUnit.SECONDS.sleep(3);
        System.out.println(diyLockDemo.getCount());
    }

    private static class DiySynchronizedLock {

        private Thread holdedThrad;

        public void lock() {
            // 让获取到锁的线程通行，让未获取到锁的线程等待阻塞
            synchronized (this) {
                while (holdedThrad != null) {
                    try {
//                        System.out.println(Thread.currentThread() + " 被阻塞");
                        wait();
//                        System.out.println(Thread.currentThread() + " 被唤醒");
                    } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread() + "interrupted");
                    }
                }
                holdedThrad = Thread.currentThread();
//                System.out.println(Thread.currentThread() + "取得锁");
            }

        }


        public void unLock() throws Exception {
            // 解锁
            // 1. 通知其他阻塞的线程
            // 2. 将当前持有的线程置空
            synchronized (this) {
                if (Thread.currentThread() != holdedThrad) {
                    throw new Exception("持有锁的线程不为当前线程");
                }
                holdedThrad = null;
                notifyAll();
            }
        }

    }
}
