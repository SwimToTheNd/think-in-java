/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.shareresources;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 偶数消费者类：消费IntGenerator生产的数字
 * 
 * @author BloodFly
 * @date 2018年4月22日
 */
public class EvenChecker implements Runnable {
	private IntGenerator generator;
	private final int id;

	public EvenChecker(IntGenerator generator, int id) {
		this.generator = generator;
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (!generator.isCanceled()) {
			int val = generator.next();
			if (val % 2 != 0) {
				System.out.println(val + " not even!");
				// Cancels all EvenCheckers
				generator.cancel();
			}
		}
	}

	// Test any type of IntGenerator
	public static void test(IntGenerator generator, int count) {
		System.out.println("Press Control-C to exit");
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < count; i++) {
			exec.execute(new EvenChecker(generator, i));
		}
		exec.shutdown();
	}

	// Default value for count:
	public static void test(IntGenerator generator) {
		test(generator, 100);
	}
}
