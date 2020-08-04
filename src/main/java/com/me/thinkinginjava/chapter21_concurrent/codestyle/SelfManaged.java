/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.codestyle;

/**
 * 自管理的Runnable
 * 
 * @author BloodFly
 * @date 2018年4月6日
 */
public class SelfManaged implements Runnable {

	private int countDown = 5;
	private Thread t = new Thread(this);

	public SelfManaged() {
		t.start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return Thread.currentThread().getName() + "(" + countDown + "), ";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
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
			new SelfManaged();
		}
	}

}
