/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.shareresources;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用显示的Lock对象
 * <p>
 * 如果在使用Synchronized关键字时，某些事物失败了，那么就会抛出一个异常。没有机会去做任何清理工作。
 * <p>
 * 有了显示的Lock对象，你就可以使用finally子句将系统维护在正确的状态
 * 
 * @author BloodFly
 * @date 2018年4月22日
 */
public class MutexEvenGenerator extends IntGenerator {

	private int currentValue = 0;
	// reentrant 折返
	private Lock lock = new ReentrantLock();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.me.thinkinginjava.chapter21_concurrent.shareresources.IntGenerator#
	 * next()
	 */
	@Override
	public int next() {
		lock.lock();
		try {
			++currentValue;
			Thread.yield();
			++currentValue;
			// return 语句必须在try子句出现，以确保unlock()不会过早发生，从而将数据暴露给了第二个任务。
			return currentValue;
		} finally {
			lock.unlock();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EvenChecker.test(new MutexEvenGenerator());
	}

}
