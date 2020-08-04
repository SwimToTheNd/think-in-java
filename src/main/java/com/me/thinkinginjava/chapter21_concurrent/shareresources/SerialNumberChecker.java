/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.shareresources;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * SerialNumberGenerator检查类
 * 
 * @see SerialNumberGenerator
 * 
 * @author BloodFly
 * @date 2018年5月6日
 */
public class SerialNumberChecker {

	private static final int SIZE = 10;
	private static CircularSet serials = new CircularSet(1000);
	private static ExecutorService exec = Executors.newCachedThreadPool();

	static class SerialChecker implements Runnable {

		/**
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			while (true) {
				int serial = SerialNumberGenerator.nextSerialNumber();
				if (serials.contain(serial)) {
					System.out.println("Duplicate: " + serial);
					System.exit(0);
				}
				// 递增不安全操作，可能被其他线程插入相同的值
				serials.add(serial);
			}
		}
	}

	/**
	 * @param args
	 * @throws InterruptedException
	 * @throws NumberFormatException
	 */
	public static void main(String[] args) throws NumberFormatException, InterruptedException {
		for (int i = 0; i < SIZE; i++) {
			exec.execute(new SerialChecker());
		}
		// Stop after n seconds if there's an argument:
		if (args.length > 0) {
			TimeUnit.SECONDS.sleep(new Integer(args[0]));
			System.out.println("No duplicate detected(检测)");
			System.exit(0);
		}
	}

}

/**
 * 可重复利用的存储set Reuses storage so we don't run out of memory
 * 
 * @author BloodFly
 * @date 2018年5月6日
 */
class CircularSet {
	private int[] array;
	private int len;
	private int index = 0;

	public CircularSet(int size) {
		array = new int[size];
		len = size;
		// Initialize to a value not produced by the SerialNumberGenerator
		for (int i = 0; i < size; i++) {
			array[i] = -1;
		}
	}

	public synchronized void add(int i) {
		array[index] = i;
		// Warp index and write over old elements
		index = ++index % len;
	}

	public synchronized boolean contain(int val) {
		for (int i = 0; i < len; i++) {
			if (array[i] == val) {
				return true;
			}
		}
		return false;
	}

}