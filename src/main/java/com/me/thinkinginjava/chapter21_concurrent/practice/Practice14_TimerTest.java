/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.practice;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 可以生成许多Timer对象，这些对象执行在定时时间到达后将执行某个简单的任务。证明Timer可以扩展到很大的数目
 * 
 * @author BloodFly
 * @date 2018年5月12日
 */
public class Practice14_TimerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		Random random = new Random();
		for (int i = 0; i < 10000; i++) {
			exec.execute(new MyTimer(new MyTimerTask(), random.nextInt(10) * 1000));
		}
		exec.shutdown();
	}

}

class MyTimer implements Runnable {

	private Timer timer;
	private TimerTask timerTask;
	private long delayTime;

	public MyTimer(MyTimerTask timerTask, long delayTime) {
		this.timer = new Timer();
		this.delayTime = delayTime;
		this.timerTask = timerTask;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		timer.schedule(timerTask, delayTime);
		System.out.println(this);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		Thread t = Thread.currentThread();
		return "after delay Time #" + delayTime + "# second Timer will be startup by: " + t;
	}

}

class MyTimerTask extends TimerTask {
	// 此处并没有并发
	private static int count = 0;
	private int id = ++count;

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.util.TimerTask#run()
	 */
	@Override
	public void run() {
		System.out.println(this);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
//		return " timer task id: #" + id + "# start run!";
		return id+"";
	}
}