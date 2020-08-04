/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.killtask;

/**
 * 同一个互斥可以被同一个任务多次获得
 * 
 * @author BloodFly
 * @date 2018年6月30日
 */
public class Multilock {

	public synchronized void f1(int count) {
		if (count-- > 0) {
			System.out.println("f1() calling f2() with count " + count);
			f2(count);
		}
	}

	/**
	 * @param count
	 */
	private synchronized void f2(int count) {
		if (count-- > 0) {
			System.out.println("f2() calling f1() with count " + count);
			f1(count);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final Multilock multilock = new Multilock();
		new Thread() {
			/**
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Thread#run()
			 */
			@Override
			public void run() {
				multilock.f1(10);
			}
		}.start();
	}

}
