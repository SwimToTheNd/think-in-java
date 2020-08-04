/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.shareresources;

/**
 * int生产者类
 * 
 * <p>
 * 诸如赋值和返回值这样的简单的操作在发生时没有中断的可能，因此，你不会看到这个处于在执行这些简单操作的过程中的中间状态。
 * 
 * @author BloodFly
 * @date 2018年4月22日
 */
public abstract class IntGenerator {
	// 为了保证可视性（原子性和可视性）
	private volatile boolean canceled = false;

	public abstract int next();

	// allow this to be canceled;
	public void cancel() {
		canceled = true;
	}

	public boolean isCanceled() {
		return canceled;
	}
}
