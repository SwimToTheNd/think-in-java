/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.exception;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 修改Executor产生线程的方式。Thread.UncaughtExceptionHandler是java
 * SE5中的新接口，它允许你在每个Thread对象上都附着一个异常处理。
 * <P>
 * Thread.UncaughtExceptionHandler.uncaughtException()会在线程因未捕获的异常而临近死亡时被调用。
 * <p>
 * 为了使用它，我们创建了一个新类型的ThreadFactory，它将在每个新创建的Thread对象上附着一个Thread.UncaughtExceptionHandler。
 * <p>
 * 我们将这个工厂传递给Executors创建新的ExecutorService方法。
 * 
 * @author BloodFly
 * @date 2018年4月7日
 */
public class CaptureUncaughtException {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool(new HandlerThreadFactory());
		exec.execute(new ExceptionThread2());
		exec.shutdown();
	}

}

class ExceptionThread2 implements Runnable {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		Thread thread = Thread.currentThread();
		System.out.println("run() by " + thread);
		System.out.println("this eh = " + thread.getUncaughtExceptionHandler());
		throw new RuntimeException();
	}
}

class MyUncaughtExceptionHandler implements UncaughtExceptionHandler {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.lang.Thread.UncaughtExceptionHandler#uncaughtException(java.lang.
	 * Thread, java.lang.Throwable)
	 */
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println(t + " caught " + e);
	}

}

class HandlerThreadFactory implements ThreadFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.concurrent.ThreadFactory#newThread(java.lang.Runnable)
	 */
	@Override
	public Thread newThread(Runnable r) {
		System.out.println(this + " creating new Thread");
		Thread thread = new Thread(r);
		System.out.println("created " + thread);
		thread.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		System.out.println("eh = " + thread.getUncaughtExceptionHandler());
		return thread;
	}

}
