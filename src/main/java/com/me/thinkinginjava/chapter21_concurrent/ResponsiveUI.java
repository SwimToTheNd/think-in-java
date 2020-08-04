/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent;

import java.io.IOException;

/**
 * 创建有响应的用户界面
 * <p>
 * 要想让程序有响应，就得把计算程序 放在run()方法中，这样它就能让出处理器给别的程序。
 * 
 * @author BloodFly
 * @date 2018年4月7日
 */
public class ResponsiveUI extends Thread {
	private static volatile double d = 1;

	/**
	 * 
	 */
	public ResponsiveUI() {
		// 设置为后台线程，主线程结束后杀死后台线程
		setDaemon(true);
		start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		while (true) {
			d = d + (Math.PI + Math.E) / d;
			System.out.println("calculate result: " + d);
		}
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// new UnResponsiveUI();
		new ResponsiveUI();
		System.in.read();
		System.out.println(d);
	}

}

class UnResponsiveUI {
	private volatile double d = 1;

	public UnResponsiveUI() throws IOException {
		while (d > 0) {
			d = d + (Math.PI + Math.E) / d;
			System.in.read(); // never get here
		}
	}
}