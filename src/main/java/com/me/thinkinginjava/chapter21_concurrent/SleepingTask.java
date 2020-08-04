/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 将yield()的调用换成调用sleep()
 * <p>
 * 异常不能跨线程传回主线程，所以必须在本地处理所有在任务内部产生的异常
 * 
 * @author BloodFly
 * @date 2018年3月18日
 */
public class SleepingTask extends LiftOff {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.me.thinkinginjava.chapter21_concurrent.LiftOff#run()
	 */
	@Override
	public void run() {
		while (countDown-- > 0) {
			System.out.println(status());
			try {
				// old style:
				// Thread.sleep(100);
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				System.err.println("Interrupted");
			}
		}
	}

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exec.execute(new SleepingTask());
		}
		exec.shutdown();
	}
}
