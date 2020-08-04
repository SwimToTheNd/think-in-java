package com.me.thinkinginjava.chapter21_concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ScheduledThreadPoolExecutor
 * create by BloodFly at 2020/3/19
 */
public class ScheduleThreadPoolDemo implements Runnable {

    private int sleepTime;

    public ScheduleThreadPoolDemo(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    public static void main(String[] args) throws InterruptedException {
        // 创建定时任务线程池
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);
        scheduledExecutorService.schedule(() -> {
            System.out.println(Thread.currentThread().getName() + "----延迟指定时间后打印任务");
        }, 2, TimeUnit.SECONDS);
        // 0s后每2s执行一次任务（以固定频率运行）
        scheduledExecutorService.scheduleAtFixedRate(new ScheduleThreadPoolDemo(3), 0, 2, TimeUnit.SECONDS);
        // 0s后每2s执行一次任务（以任务执行时间为频率运行）
        scheduledExecutorService.scheduleWithFixedDelay(new ScheduleThreadPoolDemo(4), 0, 2, TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(30);
        scheduledExecutorService.shutdown();
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(sleepTime);
            System.out.println(Thread.currentThread().getName() + " 睡眠 " + sleepTime + "s 后打印任务");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
