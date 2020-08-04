/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Callable具有返回值的任务
 * <p>
 * submit()方法会产生Future对象
 * <p>
 * 使用Future的isDone()来查询Future是否已经完成。get()将阻塞，直到结果准备就绪
 * @author BloodFly
 * @date 2018年3月18日
 */
public class CallableDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		ArrayList<Future<String>> results = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			results.add(exec.submit(new TaskWithResult(i)));
		}

		for (Future<String> fs : results) {
			try {
				System.out.println(fs.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			} catch (ExecutionException e) {
				e.printStackTrace();
			} finally {
				exec.shutdown();
			}
		}
	}

}
