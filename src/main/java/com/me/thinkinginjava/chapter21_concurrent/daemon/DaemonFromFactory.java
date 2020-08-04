/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.daemon;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 用于新DaemonThreadFactory作为参数传递给Executor.newCachedThreadPool()
 * 
 * @author BloodFly
 * @date 2018年4月5日
 */
public class DaemonFromFactory implements Runnable {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (true) {
			try {
				TimeUnit.MILLISECONDS.sleep(100);
				System.out.println(Thread.currentThread() + " " + this);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("Interrupted!");
			}

		}
	}

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		// 每个静态的ExecutorService创建方法都被重载为接受一个ThreadFactory对象，而这个对象将被用来创建新的线程
		ExecutorService exec = Executors.newCachedThreadPool(new DaemonThreadFactory());
		for (int i = 0; i < 10; i++) {
			exec.execute(new DaemonFromFactory());
		}
		System.out.println("All daemons started");
		TimeUnit.MILLISECONDS.sleep(100);
		;
	}

}
