/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.shareresources;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用显示的Lock对象来创建临界区
 * 
 * @author BloodFly
 * @date 2018年5月12日
 */
public class ExplicitCriticalSection {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PairManager pman1 = new ExplicitPairManager1(),
				pman2 = new ExplicitalPairManager2();
		CriticalSection.testApproaches(pman1, pman2);
	}

}

// Synchronize the entire method
class ExplicitPairManager1 extends PairManager {
	private Lock lock = new ReentrantLock();

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.me.thinkinginjava.chapter21_concurrent.shareresources.PairManager#increment()
	 */
	@Override
	public synchronized void increment() {
		lock.lock();
		try {
			p.incrementX();
			p.incrementY();
			store(getPair());
		} finally {
			lock.unlock();
		}
	}
}

// Use a critical section:
class ExplicitalPairManager2 extends PairManager {
	private Lock lock = new ReentrantLock();
	/**
	 * (non-Javadoc)
	 * @see com.me.thinkinginjava.chapter21_concurrent.shareresources.PairManager#increment()
	 */
	@Override
	public synchronized void increment() {
		Pair temp;
		lock.lock();
		try {
			p.incrementX();
			p.incrementY();
			temp = getPair();
		} finally {
			lock.unlock();
		}
		store(temp);
	}
}