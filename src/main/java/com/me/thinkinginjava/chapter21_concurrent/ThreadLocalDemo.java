package com.me.thinkinginjava.chapter21_concurrent;

/**
 * 当前线程副本-ThreadLocal
 * <br>
 *  ThreadLocal为每个使用该变量的线程提供独立的变量副本，每一个线程可以独立的改变自己的副本，而不会影响其他线程所对应的副本
 *  从线程角度看，目标变量就好像线程的本地变量
 *  <br>
 *  ThreadLocalMap<currentThread, Map<ThreadLocal变量，值T>>
 *
 * create by BloodFly at 2020/3/15
 */
public class ThreadLocalDemo {

    // ThreadLocalMap<currentThread, Map<seqNum，integer>>
    private ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public ThreadLocal<Integer> getSeqNum() {
        return seqNum;
    }

    /**
     * 返回下一个序列值
     *
     * @return
     */
    public Integer nextSeqNum() {
        seqNum.set(seqNum.get() + 1);
        return seqNum.get();
    }

    private static class TestClient extends Thread {
        private ThreadLocalDemo threadLocalDemo;

        public TestClient(ThreadLocalDemo threadLocalDemo) {
            this.threadLocalDemo = threadLocalDemo;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                System.out.println("thread[" + Thread.currentThread().getName() + "] ---> seqNum = [" + threadLocalDemo.nextSeqNum() + "]");
            }
            // 当线程结束后，会自动清除该线程的局部变量。显示调用该方法，加快内存回收的速度
            threadLocalDemo.getSeqNum().remove();
        }
    }

    public static void main(String[] args) {
        ThreadLocalDemo threadLocalDemo = new ThreadLocalDemo();
        TestClient testClient1 = new TestClient(threadLocalDemo);
        TestClient testClient2 = new TestClient(threadLocalDemo);
        TestClient testClient3  = new TestClient(threadLocalDemo);
        testClient1.start();
        testClient2.start();
        testClient3.start();
    }
}
