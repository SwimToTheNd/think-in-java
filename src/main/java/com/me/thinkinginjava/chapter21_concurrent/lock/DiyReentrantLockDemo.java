package com.me.thinkinginjava.chapter21_concurrent.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 可重入锁的Sysnchronized版本实现
 * <pre>
 * f() {
 *     lock()
 *       g() {
 *           lock()
 *           ...
 *           unlock()
 *       }
 *     unlock()
 * }
 * <pre>
 * create by BloodFly at 2020/3/11
 */
public class DiyReentrantLockDemo {
    private int count;
    private int count2;
    private DiyReentrantSynchronizedLock lock = new DiyReentrantSynchronizedLock();

    public void incrementCount() throws Exception {
        lock.lock();
        count++;
        incrementCount2();
        lock.unLock();
    }

    public void incrementCount2() throws Exception {
        lock.lock();
        count2++;
        lock.unLock();
    }


    public int getCount() {
        return count;
    }


    public int getCount2() {
        return count2;
    }

    public static void main(String[] args) throws InterruptedException {
        DiyReentrantLockDemo DiyReentrantLockDemo = new DiyReentrantLockDemo();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000000; i++) {
            executorService.execute(() -> {
                try {
                    DiyReentrantLockDemo.incrementCount();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("count = " + DiyReentrantLockDemo.getCount() + ", count2 = " + DiyReentrantLockDemo.getCount2());
    }

    private static class DiyReentrantSynchronizedLock {
        // 进入次数，lock()获得锁成功一次+1,unlock()释放锁成功一次-1
        private int reentrantCount;
        // 持有锁的线程
        private Thread holdedThrad;

        public void lock() {
            // 让获取到锁的线程通行，让未获取到锁的线程等待阻塞
            // 如果请求锁的当前线程与持有锁的线程一样，则让当前线程通行，进入次数+1
            synchronized (this) {
                while (reentrantCount > 0 && Thread.currentThread() != holdedThrad) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread() + "interrupted");
                    }
                }
                holdedThrad = Thread.currentThread();
                reentrantCount++;
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
                reentrantCount--;
                if (reentrantCount == 0) {
                    holdedThrad = null;
                    notifyAll();
                }
            }
        }

    }
}


