package com.me.thinkinginjava.chapter21_concurrent.sync;

/**
 * create by BloodFly at 2020/3/14
 */
public class SyncTest03 implements Runnable {

    static int i;

    /**
     * 此时this不是同一个对象时，加锁是不起作用的。此时是对象锁，
     * 将方法add改为static则synchronized是类锁
     */
    public static void add() {
        synchronized (SyncTest03.class) {
            i++;
        }
    }

    @Override
    public void run() {
        for (int j = 0; j < 10000; j++) {
            add();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SyncTest03 syncTest01 = new SyncTest03();
        SyncTest03 syncTest02 = new SyncTest03();
        Thread thread1 = new Thread(syncTest01);
        Thread thread2 = new Thread(syncTest02);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("i = " + i);
    }
}
