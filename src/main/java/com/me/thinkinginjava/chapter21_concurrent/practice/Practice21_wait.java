/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author BloodFly
 * @date 2018年7月1日
 */
public class Practice21_wait {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		Task1 task1 = new Task1();
		exec.execute(task1);
		exec.execute(new Task2(task1));
		exec.shutdown();
	}

}

class Task1 implements Runnable {

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			synchronized (this) {
				wait(); // 进入阻塞，释放锁
			}
			System.out.println("After wait(), Task1 display this message");
		} catch (InterruptedException e) {
			System.out.println("Task1 interrupt exception");
		}
	}
}

class Task2 implements Runnable {

	private Task1 task1;

	public Task2(Task1 task1) {
		this.task1 = task1;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(5);
			synchronized (task1) {
				System.out.println("Task2 after sleep, notifyAll() task1");
				task1.notifyAll();
			}
		} catch (InterruptedException e) {

		}
	}
}