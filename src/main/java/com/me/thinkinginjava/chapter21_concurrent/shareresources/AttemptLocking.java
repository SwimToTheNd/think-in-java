/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.shareresources;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 通常只有在解决特殊问题时，才使用显式的Lock对象。例如：用Synchronized关键字不能尝试着获取锁且最终获取锁会失败，或者尝试着获取一段时间，然后放弃它。
 * 
 * @author BloodFly
 * @date 2018年4月22日
 */
public class AttemptLocking {
	private ReentrantLock lock = new ReentrantLock();

	public void untimed() {
		boolean captured = lock.tryLock();
		try {
			System.out.println("tryLock(): " + captured);
		} finally {
			if (captured) {
				lock.unlock();
			}
		}
	}

	// 在指定的时间内尝试获取锁
	public void timed() {
		boolean captured = false;
		try {
			captured = lock.tryLock(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		try {
			System.out.println("tryLock(2, TimeUnit.SECONDS): " + captured);
		} finally {
			if (captured) {
				lock.unlock();
			}
		}
	}

	public static void main(String[] args) {
		final AttemptLocking al = new AttemptLocking();
		al.untimed(); // True, lock is available
		al.timed(); // True, lock is available

		new Thread() {
			{
				setDaemon(true);
			}

			public void run() {
				al.lock.lock();
				System.out.println("acquired");
			};
		}.start();
		Thread.yield(); // Give 2nd task a chance
		al.untimed(); // False, lock grabbed by task
		al.timed(); // False, lock grabbed by task
	}
}
