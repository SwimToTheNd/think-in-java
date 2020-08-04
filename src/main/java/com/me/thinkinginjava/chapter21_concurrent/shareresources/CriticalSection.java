/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.shareresources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 临界区（同步控制块）<br>
 * 有时，你只希望防止多个线程同时访问内部的部分代码而不是防止访问整个方法。通过这种方式分享出来的代码段被称为临界区（critical section）
 * 在进入同步投控制块前，必须得到syncObject对象的锁。如果其他线程已经得到这个锁，那么就得等到锁被释放以后，才能进入临界区。<br>
 * 通过使用同步控制块，而不是对整个方法进行同步控制，可以使多个任务访问对象的时间性能得到显著提高。
 * 
 * @author BloodFly
 * @date 2018年5月12日
 */
public class CriticalSection {

	static void testApproaches(PairManager pman1, PairManager pman2) {
		ExecutorService exec = Executors.newCachedThreadPool();
		PairManipulator pm1 = new PairManipulator(pman1),
				pm2 = new PairManipulator(pman2);
		PairChecker pcheck1 = new PairChecker(pman1),
				pcheck2 = new PairChecker(pman2);
		exec.execute(pm1);
		exec.execute(pm2);
		exec.execute(pcheck1);
		exec.execute(pcheck2);
		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			System.out.println("Sleep Interrupted");
			e.printStackTrace();
		}
		System.out.println("pm1: " + pm1 + "\npm2: " + pm2);
		System.exit(0);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PairManager pman1 = new PairManager1(),
				pman2 = new PairManager2();
		testApproaches(pman1, pman2);
	}

}

// Not thread-safe Class
class Pair {
	private int x, y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Pair() {
		this(0, 0);
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	public void incrementX() {
		x++;
	}

	public void incrementY() {
		y++;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "x: " + x + ", y: " + y;
	}

	public class PaiValuesNotEqualException extends RuntimeException {
		public PaiValuesNotEqualException() {
			super("Pair values not equal: " + Pair.this);
		}
	}

	public void checkState() {
		if (x != y) {
			throw new PaiValuesNotEqualException();
		}
	}
}

// 使用了模版方法设计模式
// Protect a Pair inside a thread-safe class;
abstract class PairManager {
	AtomicInteger checkCounter = new AtomicInteger(0);
	protected Pair p = new Pair();
	private List<Pair> storage = Collections.synchronizedList(new ArrayList<>());

	public synchronized Pair getPair() {
		// Make a copy to keep the original safe:
		return new Pair(p.getX(), p.getY());
	}

	// Assume this is a time consuming operation
	protected void store(Pair p) {
		storage.add(p);
		try {
			TimeUnit.MICROSECONDS.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public abstract void increment();
}

// Synchronize the entire method
class PairManager1 extends PairManager {

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.me.thinkinginjava.chapter21_concurrent.shareresources.PairManager#increment()
	 */
	@Override
	public synchronized void increment() {
		p.incrementX();
		p.incrementY();
		store(getPair());
	}
}

// use a critical section:
class PairManager2 extends PairManager {

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.me.thinkinginjava.chapter21_concurrent.shareresources.PairManager#increment()
	 */
	@Override
	public void increment() {
		Pair temp;
		synchronized (this) {
			p.incrementX();
			p.incrementY();
			temp = getPair();
		}
		store(temp);
	}
}

// 调用Pair的increment()方法，让x,y增加
class PairManipulator implements Runnable {
	private PairManager pm;

	public PairManipulator(PairManager pm) {
		this.pm = pm;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (true) {
			pm.increment();
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Pair: " + pm.getPair() + " checkCounter = " + pm.checkCounter.get();
	}
}

// checkCounter自增，检查x,y是否相等
class PairChecker implements Runnable {
	private PairManager pm;

	public PairChecker(PairManager pm) {
		this.pm = pm;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (true) {
			pm.checkCounter.incrementAndGet();
			pm.getPair().checkState();
		}
	}

}