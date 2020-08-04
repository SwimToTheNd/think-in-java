/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.collaboration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * WaxOMatic有两个过程：1：将蜡涂到Car上 2：抛光它。 在抛光任务在涂蜡任务完成之前不能执行其他工作。
 * 涂蜡任务在涂另一层蜡之前必须等待抛光任务完成。
 * 
 * @author BloodFly
 * @date 2018年7月1日
 */
public class WaxOMatic {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		Car car = new Car();
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new WaxOff(car));
		exec.execute(new WaxOn(car));
		TimeUnit.SECONDS.sleep(5);
		exec.shutdownNow(); // Interrupt all tasks
	}

}

class Car {
	// 是否已经涂蜡
	private boolean waxOn = false;

	/**
	 * 涂蜡完成
	 */
	public synchronized void waxed() {
		waxOn = true; // Ready to buff
		notifyAll();
	}

	/**
	 * 抛光完成
	 * 
	 * @throws InterruptedException
	 */
	public synchronized void buffed() {
		waxOn = false;
		notifyAll();
	}

	/**
	 * 等待涂蜡
	 * 
	 * @throws InterruptedException
	 */
	public synchronized void waitForWaxing() throws InterruptedException {
		while (waxOn == false) {
			wait();
		}

	}

	/**
	 * 等待抛光
	 * 
	 * @throws InterruptedException
	 */
	public synchronized void waitForBuffing() throws InterruptedException {
		while (waxOn == true) {
			wait();
		}
	}
}

// 涂蜡任务
class WaxOn implements Runnable {

	private Car car;

	public WaxOn(Car c) {
		this.car = c;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				System.out.println("Wax on !");
				TimeUnit.MICROSECONDS.sleep(1200);
				car.waxed(); // 标记车已经上腊完成
				car.waitForBuffing(); // 等抛光完成，阻塞涂腊任务直到抛光完成
			}
		} catch (InterruptedException e) {
			System.out.println("Exiting via interrupt");
		}
		System.out.println("Ending Wax On task");
	}

}

// 抛光任务
class WaxOff implements Runnable {
	private Car car;

	public WaxOff(Car c) {
		this.car = c;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				// 抛光任务等待涂蜡任务完成，阻塞抛光任务直到涂蜡任务完成
				car.waitForWaxing();
				System.out.println("Wax Off!");
				TimeUnit.MICROSECONDS.sleep(1200);
				//
				car.buffed();
			}
		} catch (InterruptedException e) {
			System.out.println("Exiting via interrupt");
		}
		System.out.println("Ending Wax Off task");
	}
}