package com.me.thinkinginjava.chapter21_concurrent;

import java.util.concurrent.TimeUnit;

/**
 * 线程Thread拥有的属性测试
 * create by BloodFly at 2020/3/15
 */
public class ThreadProperties implements Runnable {
    @Override
    public void run() {
        try {
            // 模拟工作
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread currentThread = Thread.currentThread();
        String curThreadName = currentThread.getName();
        System.out.println("当前线程名称：" + curThreadName);
        System.out.println("当前线程：" + curThreadName + " 线程组中活动的线程数目：" + Thread.activeCount());
        System.out.println("当前线程：" + curThreadName + " 标识符ID：" + currentThread.getId());
        System.out.println("当前线程：" + curThreadName + " 状态：" + currentThread.getState());
        System.out.println("当前线程：" + curThreadName + " 所属线程组：" + currentThread.getThreadGroup());
        System.out.println("当前线程：" + curThreadName + " 是否处于活动状态：" + currentThread.isAlive());
        System.out.println("当前线程：" + curThreadName + " 是否为守护线程：" + currentThread.isDaemon());
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadProperties threadProperties = new ThreadProperties();
        for (int i = 0; i < 5; i++) {
            new Thread(threadProperties, "线程名称(" + i + ")").start();
        }
        // 主线程
        Thread mainThread = Thread.currentThread();
        System.out.println("主线程：" + mainThread + " 线程组中活动的线程数目：" + Thread.activeCount());
        System.out.println("主线程标识符：" + mainThread.getId());
        System.out.println("主线程状态：" + mainThread.getState());
        System.out.println("主线程所属线程组：" + mainThread.getThreadGroup());
        System.out.println("主线程是否处于活动状态：" + mainThread.isAlive());
        System.out.println("主线程是否为守护线程：" + mainThread.isDaemon());
        // 睡眠便于监控
        TimeUnit.SECONDS.sleep(100);
    }
}
