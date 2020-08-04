/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.practice;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author BloodFly
 * @date 2018年4月22日
 */
public class Practice11_UnSynchronizedField extends FieldSynchronized {

	private int i = 0;
	private int j = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.me.thinkinginjava.chapter21_concurrent.practice.FieldSynchronized#
	 * judgeEqual()
	 */
	@Override
	public void judgeEqual() {
		i++;
		j++;
		setEqual(i == j);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 线程不安全的方式
		Practice11_UnSynchronizedField unSynchronizedField = new Practice11_UnSynchronizedField();
		test(unSynchronizedField, 2);
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("Synchronized way is OK");
				System.exit(1);
			}
		}, 3000);
		// 线程安全的方式
		SynchronizedField synchronizedField = new SynchronizedField();
		test(synchronizedField, 100);
	}

	public static void test(FieldSynchronized fieldSynchronized, int threadCount) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < threadCount; i++) {
			exec.execute(new SynChronizedCheck(fieldSynchronized));
		}
		exec.shutdown();
	}

}

class SynChronizedCheck implements Runnable {

	private FieldSynchronized fieldSynchronized;

	public SynChronizedCheck(FieldSynchronized fieldSynchronized) {
		this.fieldSynchronized = fieldSynchronized;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (fieldSynchronized.isEqual()) {
			fieldSynchronized.judgeEqual();
		}
		System.out.println("isEqual: " + fieldSynchronized.isEqual());
	}

}

abstract class FieldSynchronized {
	private boolean isEqual = true;

	public abstract void judgeEqual();

	public boolean isEqual() {
		return isEqual;
	}

	/**
	 * @param isEqual
	 *            the isEqual to set
	 */
	public void setEqual(boolean isEqual) {
		this.isEqual = isEqual;
	}

}

class SynchronizedField extends FieldSynchronized {
	private int i = 0;
	private int j = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.me.thinkinginjava.chapter21_concurrent.practice.FieldSynchronized#
	 * judgeEqual()
	 */
	@Override
	public synchronized void judgeEqual() {
		++i;
		j++;
		setEqual(i == j);
	}

}