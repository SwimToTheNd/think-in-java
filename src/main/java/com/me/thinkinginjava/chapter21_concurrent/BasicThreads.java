/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent;

/**
 * @author BloodFly
 * @date 2018年3月18日
 */
public class BasicThreads {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread t = new Thread(new LiftOff());
		t.start();
		System.out.println("Waiting for LiftOff");
	}

}
