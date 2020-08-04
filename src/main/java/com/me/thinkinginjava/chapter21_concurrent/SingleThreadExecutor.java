/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 只会有一个线程运行，等待任务会被加入BlockQueue阻塞队列中
 * </p>
 * SingleThreadExecutor会序列化所有提交给它的任务，并会维护它自己（隐藏）的悬挂任务队列
 * <p>
 * SingleThreadExecutor可以让你省去只是为了维持某些事物的原型而进行的各种协调努力
 * 
 * @author BloodFly
 * @date 2018年3月18日
 */
public class SingleThreadExecutor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService exec = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 5; i++) {
			exec.execute(new LiftOff());
		}
		exec.shutdown();
	}

}
