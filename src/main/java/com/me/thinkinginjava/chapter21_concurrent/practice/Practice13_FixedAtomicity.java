/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.practice;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 修复Atomicity
 * 
 * @see AtomicityTest
 * @author BloodFly
 * @date 2018年5月6日
 */
public class Practice13_FixedAtomicity implements Runnable {

	private int i = 0;

	public synchronized int getValue() {
		return i;
	}

	public synchronized void evenIncrement() {
		i++;
		i++;
	}

	public boolean isEven() {
		return getValue() % 2 == 0;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ExecutorService exec = Executors.newCachedThreadPool();
		Practice13_FixedAtomicity evenIncrement = new Practice13_FixedAtomicity();
		exec.execute(evenIncrement);
		for (int i = 0; i < 5; i++) {
			exec.execute(new FixedAtomictyChecker(evenIncrement));
		}
		exec.shutdown();
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("incremented value is even");
				System.exit(0);
			}
		}, 2000);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (true) {
			evenIncrement();
		}
	}

}

class FixedAtomictyChecker implements Runnable {

	private Practice13_FixedAtomicity evenIncrement;

	public FixedAtomictyChecker(Practice13_FixedAtomicity evenIncrement) {
		this.evenIncrement  = evenIncrement;
	}
	
	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (evenIncrement.isEven()) {
			//System.out.println("incremented value is even: " + evenIncrement.getValue());
		}
		System.out.println("incremented value is not even: " + evenIncrement.getValue());
		System.exit(0);
	}

}