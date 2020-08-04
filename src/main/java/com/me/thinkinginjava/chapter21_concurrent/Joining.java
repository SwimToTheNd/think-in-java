/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent;

/**
 * join 加入一个线程
 * <p>
 * 在一个线程A里可以调用B线程的join()方法，其效果是A线程等待一段时间直到B线程结束才继续执行。
 * <p>
 * 如果某个线程在另一个线程t上调用t.join,此线程将被挂起，直到目标线程结束才恢复（即t.isAlive()返回假）
 * <p>
 * 也可以在调用join()时带上一个超时参数（单位可以是毫秒，或者毫秒和纳秒），这样如果 目标线程在这段这时到期时还没有结束的话，join()方法总能返回
 * <p>
 * 将目标t(t.join)加入到当前线程，等待目标t线程结束后继续执行当前线程
 * 
 * @author BloodFly
 * @date 2018年4月6日
 */
public class Joining {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Sleeper sleepy = new Sleeper("Sleepy", 1500),
				grumpy = new Sleeper("Grumpy", 1500);
		Joiner dopey = new Joiner("Dopey", sleepy),
				doc = new Joiner("Doc", grumpy);
		grumpy.interrupt();
	}

}

class Sleeper extends Thread {

	private int duration; // 时间

	public Sleeper(String name, int sleepTime) {
		super(name);
		duration = sleepTime;
		start();  // 启动线程
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		try {
			sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println(getName() + " was interrupted. isInterrupted" + isInterrupted());
			return;
		}
		System.out.println(getName() + " has awakened");
	}

}

class Joiner extends Thread {
	private Sleeper sleeper;

	public Joiner(String name, Sleeper sleeper) {
		super(name);
		this.sleeper = sleeper;
		start(); // 启动线程
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		try {
			sleeper.join(); // 等待sleeper线程执行完
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("Interrupted");
		}
		System.out.println(getName() + " join completed");
	}
}
