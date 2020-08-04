/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 一次性预先执行代价高昂的线程分配，可以限制线程的数量
 * <p>
 * 在任何线池中，现有线程在可能的情况下，都会被 自动复用
 * 
 * @author BloodFly
 * @date 2018年3月18日
 */
public class FixedThreadPool {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ExecutorService exec = Executors.newFixedThreadPool(4);
		for (int i = 0; i < 5; i++) {
			exec.execute(new LiftOff());
		}
		exec.shutdown();
	}

}
