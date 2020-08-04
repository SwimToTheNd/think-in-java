/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.killtask;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 和I/O操作一样，无论在任何时刻，只要任务以不可中断的方式被阻塞，那么都有潜在的会锁住程序的可能。<br>
 * Java SE5并发类库中添加了一个特性，即在ReentrantLock上阻塞的任务具务可以被
 * 中断的能力。与在synchronized方法或临界区上阻塞的任务完全不同
 * 
 * @author BloodFly
 * @date 2018年6月30日
 */
public class Interrupting2 {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		// 主线程获得Blocked2中ReentrantLock的锁，任务中的f1方法将被阻塞
		Thread t = new Thread(new Blocked2());
		t.start();
		TimeUnit.SECONDS.sleep(2);
		System.out.println("Issuing t.interrupt()");
		t.interrupt();
	}

}

class BlockedMutexs {
	private Lock lock = new ReentrantLock();

	public BlockedMutexs() {
		// 调用构造器时，该对象将被锁住
		lock.lock();
	}

	public void f() {
		try {
			// 以可中断的方式尝试获取锁
			// This will never be available to a second task
			lock.lockInterruptibly();
		} catch (InterruptedException e) {
			System.out.println("Interrupted from lock acquisition in f()");
		}
	}
}

class Blocked2 implements Runnable {

	BlockedMutexs blocked = new BlockedMutexs();

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		System.out.println("Waiting for f() in BlockedMutex");
		blocked.f();
		System.out.println("Broken out of blocked call");
	}
}