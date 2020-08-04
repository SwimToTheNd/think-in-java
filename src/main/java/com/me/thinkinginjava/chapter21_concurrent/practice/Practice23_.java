package com.me.thinkinginjava.chapter21_concurrent.practice;

/**
 * 子线程循环10次，主线程循环100次，  一共循环50次
 * create by BloodFly at 2020/7/2
 */
public class Practice23_ {

    private static byte state = 0;

    public static void main(String[] args) {
        Practice23_ practice23_ = new Practice23_();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int executeCnt = 0;
                while (executeCnt++ < 50) {
                    synchronized (practice23_) {
                        while (state != 0) {
                            try {
                                practice23_.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        int count;
                        for (count = 0; count < 10; count++) {
                            count++;
                        }
                        System.out.println("子线程：" + count);
                        state = 1;
                        practice23_.notifyAll();
                    }
                }
            }
        });
        thread.start();

        for (int i = 0; i < 50; i++) {
            synchronized (practice23_) {
                while (state != 1) {
                    try {
                        practice23_.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int count;
                for (count = 0; count < 100; count++) {
                    count++;
                }
                System.out.println("主线程：" + count);
                state = 0;
                practice23_.notifyAll();
            }
        }
    }
}
