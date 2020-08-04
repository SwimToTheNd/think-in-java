/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 使用定制的ThreadFactory设置线程的优先级
 * 
 * @author BloodFly
 * @date 2018年4月6日
 */
public class Practice08_SimplePriorities implements Runnable {

	private volatile double d; // no optimization不被编译器优化
	private int countDown = 5;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool(new SimplePrioritiesThreadFactory(Thread.MIN_PRIORITY));
		for (int i = 0; i < 5; i++) {
			exec.execute(new Practice08_SimplePriorities());
		}
		exec.shutdown();
		exec = Executors.newCachedThreadPool(new SimplePrioritiesThreadFactory(Thread.MAX_PRIORITY));
		exec.execute(new Practice08_SimplePriorities());
		exec.shutdown();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return Thread.currentThread() + " " + countDown;
	}

}

class SimplePrioritiesThreadFactory implements ThreadFactory {

	private int priorities;

	public SimplePrioritiesThreadFactory() {
		this.priorities = Thread.NORM_PRIORITY;
	}

	public SimplePrioritiesThreadFactory(int prorities) {
		this.priorities = prorities;
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(r);
		thread.setPriority(priorities);
		return thread;
	}

}
