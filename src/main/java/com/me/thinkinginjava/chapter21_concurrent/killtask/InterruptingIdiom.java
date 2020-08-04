/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.killtask;

import java.util.concurrent.TimeUnit;

/**
 * 处理中断状态、被阻塞和不被阻塞的典型惯用法
 * 
 * @author BloodFly
 * @date 2018年6月30日
 */
public class InterruptingIdiom {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
//		if (args.length != 1) {
//			System.out.println("usage: java InterruptingIdiom delay-in-ms");
//			System.exit(1);
//		}
		Thread thread = new Thread(new Blocked3());
		thread.start();
		TimeUnit.MILLISECONDS.sleep(888);
		thread.interrupt();
		
	}

}

class NeedsCleanup {
	private final int id;

	public NeedsCleanup(int ident) {
		this.id = ident;
		System.out.println("NeedsCleanUp " + id);
	}

	public void cleanUp() {
		System.out.println("Cleaning up " + id);
	}
}

class Blocked3 implements Runnable {

	private volatile double d = 0.0;

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		
		try {
			//测试当前线程是否被中断，该方法会清除掉线程的中断状态。换名话说，如果这个方法被调用两次，那第二次调用将返回false，除非线程又一次被中断
			while (!Thread.interrupted()) {
				
				// Point1
				NeedsCleanup n1 = new NeedsCleanup(1);
				// 在定义完n1后，开始try-finally块。以保证正确的cleanup n1
				// Start try-finally immediately after definition of n1. to guarantee proper cleanup of n1:
				try {
					System.out.println("Sleeping");
					TimeUnit.SECONDS.sleep(1);
					
					// Point2
					NeedsCleanup n2 = new NeedsCleanup(2);
					// guarantee proper cleanup n2
					try {
						System.out.println("Calculating");
						// 消耗时间，不是阻塞的操作
						for (int i = 0; i < 2500000; i++) {
							d = d + (Math.PI + Math.E) / d;
						}
						System.out.println("Finished time-consuming operation");
					} finally {
						n2.cleanUp();
					}
					
				} finally {
					n1.cleanUp();
				}
			}
			System.out.println("Exiting via while() test");
		} catch (InterruptedException e) {
			System.out.println("Exiting via InterruptedException");
		}
	}

}