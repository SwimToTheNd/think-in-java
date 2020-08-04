/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 如果你知道将要在代码中处处使用相同的异常处理器，那么简单的方式是在Thread类中设置一个静态域，并将这个处理器设置为默认的未捕获异常处理器
 * <P>
 * 这个处理器只有在不存在线程专有的未捕获异常处理的情况下才会被调用。系统会检查线程专有版本，如果没有发现，则检查线程组是否有专有的uncaughtException()方法
 * 如果也没有，再调用defaultUncaugthExceptionHandler
 * 
 * @author BloodFly
 * @date 2018年4月22日
 */
public class SettingDefaultHandler {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 设置线程默认的未捕获异常处理器
		Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new ExceptionThread());
		exec.execute(new ExceptionThread2());
	}

}
