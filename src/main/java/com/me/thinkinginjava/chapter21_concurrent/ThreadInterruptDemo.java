package com.me.thinkinginjava.chapter21_concurrent;

import java.util.concurrent.TimeUnit;

/**
 * Thread.interrupted() 检查当前是否已经中断，并将该线程中判断状态清除
 * <br>
 * Thread.currentThread().isInterrupted() 检查线程是否被中断
 * <br>
 * thread.interrupt()中断线程
 * create by BloodFly at 2020/3/15
 */
public class ThreadInterruptDemo implements Runnable {
    @Override
    public void run() {
        System.out.println("thread is running!");
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("interrupted=" + Thread.currentThread().isInterrupted());
            long now = System.currentTimeMillis();
            while (System.currentTimeMillis() - now < 1000) {

            }
        }
        System.out.println("thread is interrupted! interrupted=" + Thread.currentThread().isInterrupted());
        Thread.interrupted();
        System.out.println("after Thread.interrupted, interrupted=" + Thread.currentThread().isInterrupted());
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ThreadInterruptDemo());
        thread.start();
        TimeUnit.MILLISECONDS.sleep(1000);
        thread.interrupt();

    }
}
