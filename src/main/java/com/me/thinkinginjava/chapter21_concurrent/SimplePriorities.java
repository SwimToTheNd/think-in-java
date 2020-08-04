/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程优先级
 * <p>
 * 线程的优先级将该线程的重要性传递给调度器。尽管 CPU处理现有线程集的顺序是不确定，但是调度器将倾向于让优先权高的线程先执行
 * <p>
 * 优先级低的线程仅仅是执行的频率较低
 * <p>
 * 向控制台打印不能被中断，数学运算是可以中断的。
 * <P>
 * 可移植的优先级有三个：MIN_PRIORITY、NORM_PRIORITY、MAX_PRIORITY
 * 
 * @author BloodFly
 * @date 2018年3月18日
 */
public class SimplePriorities implements Runnable {
	private int countDown = 5;
	private volatile double d; // no optimization不被编译器优化
	private int priority;

	public SimplePriorities(int priority) {
		this.priority = priority;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// Thread.currentThread()获得对驱动该任务的Thread对象的引用
		return Thread.currentThread() + ":" + countDown;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		Thread.currentThread().setPriority(priority);
		while (true) {
			// an expensive, interruptable operation:
			for (int i = 1; i < 100000; i++) {
				d += (Math.PI + Math.E) / (double) i;
				if (i % 1000 == 0) {
					Thread.yield();
				}
			}
			System.out.println(this);
			if (--countDown == 0) {
				return;
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exec.execute(new SimplePriorities(Thread.MIN_PRIORITY));
	}
		exec.execute(new SimplePriorities(Thread.MAX_PRIORITY));
		exec.shutdown();
	}

}
