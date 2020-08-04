/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.daemon;

import java.util.concurrent.TimeUnit;

/**
 * 后台线程在不执行finally子句的情况下就会终止其run()方法
 * <p>
 * 当最后一个非后台线程终止时，后台线程会“突然”终止，因此一旦main()退出，JVM就会立即关闭所有的后台进程，而不会有任何你希望出现的确认形式
 * 
 * @author BloodFly
 * @date 2018年4月6日
 */
public class DaemonDontRunFinally {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(new ADaemon());
		thread.setDaemon(true);	
		thread.start();
		TimeUnit.MILLISECONDS.sleep(1000);
	}

}

class ADaemon implements Runnable {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		System.out.println("Starting ADaemon");
		try {
			TimeUnit.MILLISECONDS.sleep(1200);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("Interrupted");
		} finally {
			// 主进程结束后不一定会执行后台线程的finally方法
			System.out.println("This should always run?");
		}
	}
}