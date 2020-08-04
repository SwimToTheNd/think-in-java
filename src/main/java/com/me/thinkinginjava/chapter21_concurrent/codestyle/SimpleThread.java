/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.codestyle;

/**
 * 任务类直接继承于Thread类,重写run()方法
 * 
 * @author BloodFly
 * @date 2018年4月6日
 */
public class SimpleThread extends Thread {
	private int countDown = 5;
	private static int threadCount = 0;

	public SimpleThread() {
		// Store the thread name
		super(Integer.toString(++threadCount));
		start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#toString()
	 */
	@Override
	public String toString() {
		return "#" + getName() + "(" + countDown + "), ";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		while (true) {
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
		for (int i = 0; i < 5; i++) {
			new SimpleThread();
		}
	}

}
