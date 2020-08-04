package com.me.thinkinginjava.chapter21_concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * create by BloodFly at 2020/3/10
 */
public class FutureTaskDemo {

    public static void main(String[] args) {
        FutureTask futureTask = new FutureTask(new TaskWithResult(1));
        Thread thread = new Thread(futureTask);
        thread.start();
        // get方法会阻塞
        try {
            Object o = futureTask.get();
            System.out.println(o);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
