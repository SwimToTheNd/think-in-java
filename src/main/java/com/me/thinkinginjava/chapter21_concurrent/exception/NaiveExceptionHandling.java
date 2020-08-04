/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 天真的; 幼稚的; 单纯的; 捕获线程异常
 * 
 * @author BloodFly
 * @date 2018年4月7日
 */
public class NaiveExceptionHandling {
	public static void main(String[] args) {
		try {
			ExecutorService executorService = Executors.newCachedThreadPool();
			executorService.execute(new ExceptionThread());
		} catch (Exception e) {
			System.out.println("Thread Exception has been handled!");
		}
	}
}
