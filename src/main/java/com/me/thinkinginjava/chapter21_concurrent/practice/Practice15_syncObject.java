/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.practice;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author BloodFly
 * @date 2018年5月12日
 */
public class Practice15_syncObject {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		SynObject synObject = new SynObject();
		for (int i = 0; i < 5; i++) {
			exec.execute(new SynObjectF(synObject));
			exec.execute(new SynObjectG(synObject));
			exec.execute(new SynObjectH(synObject));
			exec.execute(new SynObjectInnerCheck(synObject));
		}
		exec.shutdown();
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				// exec.shutdownNow();
				System.out.println("Abort！");
				System.exit(0);
			}
		}, 5000);
	}

}

class SynObjectInnerCheck implements Runnable {
	private SynObject synobject;

	public SynObjectInnerCheck(SynObject synobject) {
		this.synobject = synobject;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (true) {
			int val = synobject.getObjectInner().getI();
			if (val % 2 != 0) {
				System.err.println(val + " is not even!");
				System.exit(0);
			}
		}
	}

}

class SynObjectF implements Runnable {

	private SynObject synObject;

	public SynObjectF(SynObject synObject) {
		this.synObject = synObject;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (!Thread.interrupted()) {
			synObject.f();
		}
	}
}

class SynObjectG implements Runnable {

	private SynObject synObject;

	public SynObjectG(SynObject synObject) {
		this.synObject = synObject;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (!Thread.interrupted()) {
			synObject.g();
		}
	}
}

class SynObjectH implements Runnable {

	private SynObject synObject;

	public SynObjectH(SynObject synObject) {
		this.synObject = synObject;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (!Thread.interrupted()) {
			synObject.h();
		}
	}
}

class SynObject {

	private SynObjectInner objectInner = new SynObjectInner();;

	public void f() {
		synchronized (objectInner) {
			// System.out.print("f()");
			objectInner.increment();
		}
	}

	public void g() {
		synchronized (objectInner) {
			// System.out.print("g()");
			objectInner.increment();
		}
	}

	public void h() {
		synchronized (objectInner) {
			// System.out.print("h()");
			objectInner.increment();
		}
	}

	/**
	 * @return the objectInner
	 */
	public SynObjectInner getObjectInner() {
		return objectInner;
	}
}

class SynObjectInner {
	private int i;

	public void increment() {
		i++;
		i++;
	}

	/**
	 * @return the i
	 */
	public int getI() {
		synchronized (this) {
			return i;
		}
	}

}