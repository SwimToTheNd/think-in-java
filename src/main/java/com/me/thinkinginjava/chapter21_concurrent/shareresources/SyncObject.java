/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.shareresources;


/**
 * synchronized块必须给定一个在其上进行同步的对象，并且最合理的方式是，使用其方法正在被调用的当前对象：synchronized(this)
 * 这正是PairManager2所用的方式。在这种方式中，如果
 * 获得了synchronized块上的锁，那么该对象其他的synchronized方法和临界区就不能被调用了。
 * 因此，如果在this上同步，临界区的效果就会直接缩小在同步的范围内 有时必须在另一个对象上同步，这样做就必须确保所有相关的任务都是在同一个对象上同步的。
 * 
 * @author BloodFly
 * @date 2018年5月12日
 */
public class SyncObject {

	/**
	 * f()和g()同时运行，两个同步是互相独立的，任何一个方法都没有因为另一个方法的同步而被阻塞
	 * 被加锁的对象不一样
	 * @param args
	 */
	public static void main(String[] args) {
		final DualSynch ds = new DualSynch();
		new Thread() {
			@Override
			public void run() {
				ds.f();
			};
		}.start();
		ds.g();
	}

}

class DualSynch {

	private Object syncObject = new Object();

	public synchronized void f() {
		for (int i = 0; i < 5; i++) {
			System.out.println("f()");
			Thread.yield();
		}
	}

	public void g() {
		synchronized (syncObject) {
			for (int i = 0; i < 5; i++) {
				System.out.println("g()");
				Thread.yield();
			}
		}
	}
}