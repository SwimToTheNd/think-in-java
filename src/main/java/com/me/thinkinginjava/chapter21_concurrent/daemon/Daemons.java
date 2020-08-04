/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.daemon;

import java.util.concurrent.TimeUnit;

/**
 * 后台线程创建的所有线程都是后台线程
 * 
 * @author BloodFly
 * @date 2018年4月5日
 */
public class Daemons {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		Thread dThread = new Thread(new Daemon());
		dThread.setDaemon(true);
		dThread.start();
		System.out.println("dThread.isDaemon() = " + dThread.isDaemon() + ". ");
		// 让后台线程得以执行
		TimeUnit.MILLISECONDS.sleep(100);
	}

}

/**
 * 内部类-后台线程
 * 
 * @author BloodFly
 * @date 2018年4月5日
 */
class Daemon implements Runnable {

	/**
	 * 后台线程Daemon创建的线程数组
	 */
	private Thread[] t = new Thread[10];

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		for (int i = 0; i < t.length; i++) {
			t[i] = new Thread(new DaemonSpawn());
			t[i].start();
			System.out.println("DaemonSpawn " + i + " started");
		}
		for (int i = 0; i < t.length; i++) {
			System.out.println("t[" + i + "].isDaemon() = " + t[i].isDaemon());
		}
		while (true) {
			Thread.yield();
		}
	}

}

/**
 * 后台线程Daemon创建的子线程DaemonSpawn
 * 
 * @author BloodFly
 * @date 2018年4月5日
 */
class DaemonSpawn implements Runnable {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (true) {
			Thread.yield();
		}
	}

}