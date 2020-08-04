/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.practice;

import java.util.Random;

/**
 * 创建一个任务，它可以产生同n个斐波纳契数字组成的序列
 * <p>
 * 其中n是通过任务的构造器而提供。
 * <p>
 * 使用线程创建大量的这种任务并驱动它们。
 * 
 * @author BloodFly
 * @date 2018年3月18日
 */
public class Practice02_Fibonacci implements Runnable {
	private static int taskCount = 0;
	private final int id = taskCount++;
	private int count;

	public Practice02_Fibonacci() {
	}

	public Practice02_Fibonacci(int n) {
		this.count = n;
	}

	public int getFibonacci(int n) {
		if (n == 1 || n == 2) {
			return 1;
		}
		return getFibonacci(n - 1) + getFibonacci(n - 2);
	}

	@Override
	public String toString() {
		return "Thread#" + id + "# 前[" + this.count + "]个斐波纳契序列为：";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		StringBuilder sb = new StringBuilder(this.toString());
		for (int i = 1; i <= count; i++) {
			sb.append(" ").append(getFibonacci(i));
		}
		System.out.println(sb);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Random random = new Random();
		for (int i = 0; i < 100; i++) {
			new Thread(new Practice02_Fibonacci(random.nextInt(20))).start();
		}
	}

}
