/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.shareresources;

/**
 * 偶数生产者类
 * <p>
 * 递增程序自身也需要多个步骤，并且在递增过程中任务可能会被线程机制挂起——也就是说，在Java中，递增不是原子性的操作。
 * 因此，如果不保护任务，即使单一的递增也不是安全的。
 * 
 * @author BloodFly
 * @date 2018年4月22日
 */
public class EvenGenerator extends IntGenerator {

	private int currentEvenValue = 0;

	/**
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.me.thinkinginjava.chapter21_concurrent.shareresources.IntGenerator#
	 * next()
	 */
	@Override
	public int next() {
		// Danger point here!
		++currentEvenValue;
		++currentEvenValue;
		return currentEvenValue;
	}

	public static void main(String[] args) {
		EvenChecker.test(new EvenGenerator());
	}

}
