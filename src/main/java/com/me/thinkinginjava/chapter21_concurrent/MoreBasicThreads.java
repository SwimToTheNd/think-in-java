/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent;

/**
 * @author BloodFly
 * @date 2018年3月18日
 */
public class MoreBasicThreads {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			new Thread(new LiftOff()).start();
		}
		System.out.println("Waiting for LiftOff");
	}

}
