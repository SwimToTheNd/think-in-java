/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 由于线程的本质特性，使得你不能捕获从线程中逃逸的异常。一旦异常逃出任务的run()方法，它就会向外传播到控制台，除非你采取 特殊的步骤捕获这种错误的异常。
 * <p>
 * 可以使用Executor来解决这个问题
 * 
 * @author BloodFly
 * @date 2018年4月7日
 */
public class ExceptionThread implements Runnable {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		throw new RuntimeException();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(new ExceptionThread());
	}

}
