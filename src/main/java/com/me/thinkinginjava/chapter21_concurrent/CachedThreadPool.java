/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用Executor代替MoreBasicThreads
 * <p>
 * 在程序执行过程中通常会创建与所需数量相同的线程，然后在它回收旧线程时停止创建新线程
 * 
 * @author BloodFly
 * @date 2018年3月18日
 */
public class CachedThreadPool {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			exec.execute(new LiftOff());
		}
		// 防止新的任务提交给这个Executor
		exec.shutdown();
	}

}
