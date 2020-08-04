/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.shareresources;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子类 JavaSE5引入 了诸如AtomicInteger,AtomicLong,AtomicReference等特殊的原子性变量类
 * 这些类被调整为可以使用在某些现代处理器上的可获得的，并且在机器级别上的原子性。 一般用于涉及性能调优时
 * 
 * @author BloodFly
 * @date 2018年5月6日
 */
public class AtomicIntegerTest implements Runnable {
	private AtomicInteger i = new AtomicInteger(0);

	public int getValue() {
		return i.get();
	}

	public void evenIncrement() {
		i.getAndAdd(2);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (true) {
			evenIncrement();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				System.err.println("Aborting");
				System.exit(0);
			}
		}, 5000);
		ExecutorService exec = Executors.newCachedThreadPool();
		AtomicIntegerTest ait = new AtomicIntegerTest();
		exec.execute(ait);
		while (true) {
			int val = ait.getValue();
			if (val % 2 != 0) {
				System.out.println(val);
				System.exit(0);
			}
		}
	}

}
