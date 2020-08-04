/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.daemon;

import java.util.concurrent.TimeUnit;

/**
 * 后台线程
 * <p>
 * 后台(daemon)线程,是指在程序运行的时候在后台提供一种通用服务的线程，并且这种线程并不属于程序中不可或缺的部分。
 * <p>
 * 因此，当所有的非后台线程结束时，程序也就终止了。同时会杀死进程中的所有后台程序。
 * <p>
 * 返回过来，只要有任何非后台线程还在运行，程序就不会终止。比如，执行的main就是一个非后台线程。
 * 
 * @author BloodFly
 * @date 2018年4月5日
 */
public class SimpleDaemon implements Runnable {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			TimeUnit.MILLISECONDS.sleep(1000);
			System.out.println(Thread.currentThread() + " " + this);
		} catch (InterruptedException e) {
			System.out.println("sleep() interrupted!");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			Thread daemon = new Thread(new SimpleDaemon());
			daemon.setDaemon(true); // 将线程设置为后台线程
			daemon.start();
		}
		System.out.println("All daemons started");
		// 让主线程（main）睡眠100ms,从而验证主线程结束后所有后台线程也将结束
		TimeUnit.MILLISECONDS.sleep(1000);
	}

}
