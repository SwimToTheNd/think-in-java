/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.practice;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * @author BloodFly
 * @date 2018年6月30日
 */
public class Practice18_TimmerInterrupt {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		IntervalSleep intervalSleep = new IntervalSleep(new Timer(), 1000, 2000);
		Thread t = new Thread(new IntervalSleepRun(intervalSleep));
		t.start();
		TimeUnit.SECONDS.sleep(2);
		t.interrupt();
	}

}

class IntervalSleep {

	private final Timer timer;
	private final long sleepTime;
	private final long interval;

	/**
	 * 
	 */
	public IntervalSleep(Timer timer, long sleepTime, long interval) {
		this.timer = timer;
		this.sleepTime = sleepTime;
		this.interval = interval;
	}

	public void schedule2Sleep() {
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				System.out.println("After" + interval + "ms, Going to Sleep " + sleepTime + " ms");
				try {
					TimeUnit.MILLISECONDS.sleep(sleepTime);
				} catch (InterruptedException e) {
					System.out.println("InterruptedException " + this);
				}
			}
		}, 0, interval);
	}

	/**
	 * @return the timer
	 */
	public Timer getTimer() {
		return timer;
	}
}

/**
 * 调用间隔一定时间非任务对象的方法 的任务类
 * 
 * @author BloodFly
 * @date 2018年6月30日
 */
class IntervalSleepRun implements Runnable {

	private final IntervalSleep intervalSleep;

	public IntervalSleepRun(IntervalSleep intervalSleep) {
		this.intervalSleep = intervalSleep;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		intervalSleep.schedule2Sleep();
		while (true) {
			Boolean isInterrupted = Thread.currentThread().isInterrupted();
			if (isInterrupted) {
				System.out.println(Thread.currentThread().getClass().getName() + " " + isInterrupted);
				intervalSleep.getTimer().cancel();
				break;
			}
		}
	}
}