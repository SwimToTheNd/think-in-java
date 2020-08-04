/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.killtask;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 装饰花园，统计每天从四个大门进入花园的人数
 * 
 * @author BloodFly
 * @date 2018年5月13日
 */
public class OrnamentalGarden {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exec.execute(new Entrance(i));
		}
		TimeUnit.SECONDS.sleep(3);
		Entrance.cancel();
		exec.shutdown();
		// 等待每个任务结束，如果 所有的任务在超时时间达到之前全部结束，则返回true，否则 返回false，表示 不是所有任务都已经结束 了。
		if (!exec.awaitTermination(250, TimeUnit.MICROSECONDS)) {
			System.out.println("Some tasks were not terminated!");
		}
		System.out.println("Total: " + Entrance.getTotalCount());
		System.out.println("Sum of Entrances: " + Entrance.sumEntrances());
	}

}

class Count {
	private int count = 0;
	private Random rand = new Random(47);

	public synchronized int increment() {
		int temp = count;
		if (rand.nextBoolean()) { // Yield half the time
			Thread.yield();
		}
		return count = ++temp;
	}

	public synchronized int value() {
		return count;
	}
}

class Entrance implements Runnable {

	private static Count count = new Count();
	private static List<Entrance> entrances = new ArrayList<>();
	private static volatile boolean canceled = false;
	private int number = 0;
	// Doesn't need synchronized to read
	private final int id;

	// Atomic operation on a volatile field:
	public static void cancel() {
		canceled = true;
	}

	public Entrance(int id) {
		this.id = id;
		// Keep this task in a list. Also prevents garbage collection of dead
		// tasks:
		entrances.add(this);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (!canceled) {
			synchronized (this) {
				++number;
			}
			System.out.println(this + " Total: " + count.increment());
			try {
				TimeUnit.MICROSECONDS.sleep(100);
			} catch (InterruptedException e) {
				System.out.println("Sleep is interrupted");
			}
			System.out.println("Stopping " + this);
		}
	}

	public synchronized int getValue() {
		return number;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Entrance " + id + ": " + getValue();
	}

	public static int getTotalCount() {
		return count.value();
	}

	public static int sumEntrances() {
		int sum = 0;
		for (Entrance entrance : entrances) {
			sum += entrance.getValue();
		}
		return sum;
	}
}