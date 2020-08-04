/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 使用多线程输出n个斐波拉契序列之和
 * 
 * @author BloodFly
 * @date 2018年3月18日
 */
public class Practice05_SumOfFibonacci implements Callable<Integer> {
	private static int taskCount = 0;
	private final int id = taskCount++;
	private int count;
	private List<Integer> fibonacciList;

	public Practice05_SumOfFibonacci(int count) {
		this.count = count;
		fibonacciList = new ArrayList<>(count);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "#" + id + "# " + fibonacciList;
	}

	public int getFibonacci(int n) {
		if (n == 1 || n == 2) {
			return 1;
		}
		return getFibonacci(n - 1) + getFibonacci(n - 2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.concurrent.Callable#call()
	 */
	@Override
	public Integer call() throws Exception {
		int result = 0;
		for (int i = 1; i <= count; i++) {
			int ne = getFibonacci(i);
			fibonacciList.add(ne);
			result += ne;
		}
		System.out.println(this);
		return result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		Random random = new Random();
		List<Future<Integer>> results = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			results.add(exec.submit(new Practice05_SumOfFibonacci(random.nextInt(20))));
		}

		for (Future<Integer> future : results) {
			try {
				System.out.println(future.get());
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
