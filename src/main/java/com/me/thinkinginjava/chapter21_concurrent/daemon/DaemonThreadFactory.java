package com.me.thinkinginjava.chapter21_concurrent.daemon;

import java.util.concurrent.ThreadFactory;

/**
 * 通过编写定制的ThreadFactory可以定制由Executor创建的线程的属性（后台，优先级，名称）
 * 
 * @author BloodFly
 * @date 2018年4月5日
 */
public class DaemonThreadFactory implements ThreadFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.concurrent.ThreadFactory#newThread(java.lang.Runnable)
	 */
	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setDaemon(true);
		return t;
	}

}
