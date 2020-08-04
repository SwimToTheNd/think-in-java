/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent;

import java.util.concurrent.Callable;

/**
 * Runnable接口是执行工作的独立任务，但是它不返回任何值。
 * <p>
 * Callable在任务完成时能够返回一值，具有类型参数的泛型，类型参数表示从方法call()中返回值的类型
 * <p>
 * 使用ExecutorService.submit()方法调用
 * 
 * @author BloodFly
 * @date 2018年3月18日
 */
public class TaskWithResult implements Callable<String> {
	private int id;

	public TaskWithResult(int id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.concurrent.Callable#call()
	 */
	@Override
	public String call() throws Exception {
		return "result of TaskWithResult " + id;
	}

}
