/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.shareresources;

/**
 * 同步控制EvenGenerator
 * 
 * @author BloodFly
 * @date 2018年4月22日
 */
public class SynchronizedEvenGenerator extends IntGenerator {

	private int currentEvenValue = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.me.thinkinginjava.chapter21_concurrent.shareresources.IntGenerator#
	 * next()
	 */
	@Override
	public synchronized int next() {
		++currentEvenValue;
		// Cause failure faster
		Thread.yield();
		++currentEvenValue;
		return currentEvenValue;
	}

	public static void main(String[] args) {
		EvenChecker.test(new SynchronizedEvenGenerator());
	}
}
