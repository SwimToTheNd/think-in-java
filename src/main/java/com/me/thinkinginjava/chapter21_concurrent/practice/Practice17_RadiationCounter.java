/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author BloodFly
 * @date 2018年5月13日
 */
public class Practice17_RadiationCounter {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		RadiationCounter radiationCounter = new RadiationCounter();
		ExecutorService exec = Executors.newCachedThreadPool();
		List<Sensor> sensors = new ArrayList<>();
		int sensorCount = 1000;
		for (int i = 0; i < sensorCount; i++) {
			Sensor sensor = new Sensor(radiationCounter);
			sensors.add(sensor);
			exec.execute(sensor);
		}
		TimeUnit.SECONDS.sleep(2);
		testTotalnumber(radiationCounter, sensors);
		TimeUnit.SECONDS.sleep(2);
		radiationCounter.cancel();
		// 关闭线程池
		// 对以前提交的任务执行有序的关闭操作，但不会接受新的任务。对已经关闭的任务不会产生影响
		// 此方法不会等待以前提交的任务完成执行。
		exec.shutdown();
		// 这个方法会造成阻塞，直到所有的任务完成执行，或者超时发生，或者线程被中断
		while (!exec.awaitTermination(250, TimeUnit.MILLISECONDS)) {
			System.out.println("Some sensor havn't be closed, wait to close");
		}
		testTotalnumber(radiationCounter, sensors);

	}

	public static void testTotalnumber(RadiationCounter radiationCounter, List<Sensor> sensors) {
		synchronized (radiationCounter) {
			int totalCount = 0;
			for (Sensor sensor : sensors) {
				totalCount += sensor.getNumber();
			}
			System.out.println("totalCount in RadiationCounter: " + radiationCounter.getCount());
			System.out.println("totalCount is Sensor: " + totalCount);
		}
	}

}

/**
 * 辐射计数器
 * 
 * @author BloodFly
 * @date 2018年5月13日
 */
class RadiationCounter {

	private int count;
	private volatile boolean canceled = false;

	public synchronized void increment() {
		count++;
	}

	public synchronized int getCount() {
		return count;
	}

	/**
	 * @return the canceled
	 */
	public void cancel() {
		canceled = true;
	}

	/**
	 * @return the canceled
	 */
	public boolean isCanceled() {
		return canceled;
	}
}

/**
 * 辐射传感器
 * 
 * @author BloodFly
 * @date 2018年5月13日
 */
class Sensor implements Runnable {
	private RadiationCounter counter;
	private int number;

	public Sensor(RadiationCounter counter) {
		this.counter = counter;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (!counter.isCanceled()) {
//			synchronized (this) {
//				number++;
//			}
			number++;
			counter.increment();
		}
	}

	/**
	 * 是否不用同步也可以？
	 * 
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}
}