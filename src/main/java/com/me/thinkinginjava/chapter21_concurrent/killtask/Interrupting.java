/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.killtask;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 用Executor展示基本的interrupt()方法
 * 
 * @author BloodFly
 * @date 2018年6月3日
 */
public class Interrupting {

	private static ExecutorService exec = Executors.newCachedThreadPool();

	static void test(Runnable runnable) throws InterruptedException {
		Future<?> f = exec.submit(runnable);
		TimeUnit.MILLISECONDS.sleep(100);
		System.out.println("Interrupting " + runnable.getClass().getName());
		f.cancel(true); // Interrupts if running
		System.out.println("Interrupt sent to " + runnable.getClass().getName());
	}

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		test(new SleepBlocked());
		test(new IOBlocked(System.in));
		test(new SynchronizedBlocked());
		TimeUnit.SECONDS.sleep(5);
		System.out.println("Aborting with System.exit(0)");
		System.exit(0);
	}

}

/**
 * sleep阻塞可以被中断 <br>
 * 抛出InterruptedException异常的操作能够被中断
 * 
 * @author BloodFly
 * @date 2018年6月3日
 */
class SleepBlocked implements Runnable {

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			System.out.println("sleep isInterrupted: " + Thread.currentThread().isInterrupted());
			TimeUnit.SECONDS.sleep(100);
		} catch (InterruptedException e) {
			System.out.println("Interrupted-Exception"); // 中断异常被捕获后，线程的中断状态重置为false
			System.out.println("sleep isInterrupted: " + Thread.currentThread().isInterrupted());
		}
		System.out.println("Exiting SleepBlocked.run()");
	}
}

/**
 * IO阻塞不能被中断，可以通过关闭资源中断
 * 
 * @author BloodFly
 * @date 2018年6月3日
 */
class IOBlocked implements Runnable {
	private InputStream in;

	public IOBlocked(InputStream in) {
		this.in = in;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			System.out.println("waiting for read()");
			in.read();
		} catch (IOException e) { // IO不能被中断，以下内容还没有执行。
			// 检查是否是由于线程中断引起的异常
			if (Thread.currentThread().isInterrupted()) {
//				System.out.println(Thread.currentThread().isInterrupted());
				System.out.println("interrupted from blocked I/O");
			} else {
				throw new RuntimeException(e);
			}
		}
		System.out.println("Exiting IOBlocked.run()");
	}
}

/**
 * 线程同步引起的阻塞不能被中断
 * 
 * @author BloodFly
 * @date 2018年6月3日
 */
class SynchronizedBlocked implements Runnable {

	public synchronized void f() {
		while (true) {
			Thread.yield();
		}
	}

	public SynchronizedBlocked() {
		new Thread() {
			/**
			 * (non-Javadoc)
			 * 
			 * @see com.me.thinkinginjava.chapter21_concurrent.killtask.SynchronizedBlocked#run()
			 */
			@Override
			public void run() {
				// 锁被这个线程获得
				// Lock acquired by this thread
				f();
			}
		}.start();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		System.out.println("Trying to call f()");
		f(); // 同步阻塞不能被中断，以下内容还没有执行
		System.out.println(Thread.currentThread().isInterrupted());
		System.out.println("Exiting SynchronizedBlocked.run()");
	}
}