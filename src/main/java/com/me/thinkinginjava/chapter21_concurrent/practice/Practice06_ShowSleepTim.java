/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.practice;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 让任务输出睡眠的时间
 * 
 * @author BloodFly
 * @date 2018年3月18日
 */
public class Practice06_ShowSleepTim implements Runnable {
	private static int taskCount = 0;
	private int id = taskCount++;
	private int sleepTime;

	public Practice06_ShowSleepTim() {
		// sleep [0,10) seconds
		this.sleepTime = new Random().nextInt(11) * 1000;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "#" + id + "# has sleeped " + sleepTime + " ms";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			TimeUnit.MILLISECONDS.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(this);
	}

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			exec.execute(new Practice06_ShowSleepTim());
		}
		exec.shutdown();
	}
}
