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
public class Practice22_BusyWaiting {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		Flag flag = new Flag();
		exec.execute(new ChangeFlag(flag));
		exec.execute(new BusyWaiting(flag));
		exec.execute(new ChangeFlag2(flag));
		exec.execute(new Waiting(flag));
		exec.shutdown();
	}

}

class Flag {
	private boolean flag = false;

	public boolean getflag() {
		return flag;
	}

	/**
	 * @param flag
	 *            the flag to set
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}

class ChangeFlag implements Runnable {

	private Flag flag;

	public ChangeFlag(Flag flag) {
		this.flag = flag;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(3);
			synchronized (flag) {
				flag.setFlag(true);
			}
		} catch (InterruptedException e) {
			System.out.println("InterruptedException");
		}
	}

}

class ChangeFlag2 implements Runnable {

	private Flag flag;

	public ChangeFlag2(Flag flag) {
		this.flag = flag;
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
			synchronized (flag) {
				flag.setFlag(true);
				flag.notifyAll();
			}
		} catch (InterruptedException e) {
			System.out.println("InterruptedException");
		}
	}

}

class BusyWaiting implements Runnable {
	private Flag flag;

	public BusyWaiting(Flag flag) {
		this.flag = flag;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		int count = 0;
		while (true) {
			synchronized (flag) {
				count++;
				if (flag.getflag()) {
					System.out.println("Some task has set flg to true, while loop has been executed " + count);
					break;
				}
			}
		}
	}

}

class Waiting implements Runnable {
	private Flag flag;

	public Waiting(Flag flag) {
		this.flag = flag;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			int count = 0;
			while (!Thread.interrupted()) {
				count++;
				synchronized (flag) {
					if (!flag.getflag()) {
						flag.wait();
					} else {
						System.out.println("Flag is set to true by some task! while loop has been executed  " + count);
						break;
					}
				}
			}
		} catch (InterruptedException e) {
			System.out.println("Exiting via InterruptedException");
		}
	}

}