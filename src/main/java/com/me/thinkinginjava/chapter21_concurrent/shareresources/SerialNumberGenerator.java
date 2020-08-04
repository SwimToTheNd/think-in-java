/**
 * 
 */
package com.me.thinkinginjava.chapter21_concurrent.shareresources;

/**
 * 
 * C++递增通常可以作为一条微处理指令来实现（尽管 不可能会期望是以任何可靠的、跨平台实现）。<br>
 * Java递增操作不是原子性的，并且涉及一个读操作和一个写操作。<br>
 * 基本上，如果 一个域可能会被多个任务同时访问，或者这些任务至少有一个是写入任务，那么你应该将个这域设置为volatile的。<br>
 * 如果你将一个域定义为volatile，那么它就会告诉编译器不要执行任何移除读取和写入操作的优化，这些操作的目的是用线程中的局部变量维护对这个域的精确同步。<br>
 * 实际上，读取和写入都是直接针对内存的，而却没有被缓存。但是volatile并不能对递增不是原子型操作这一事实产生影响（volatile不能让递增操作变为为原子性操作）。<br>
 * 
 * 
 * @author BloodFly
 * @date 2018年5月6日
 */
public class SerialNumberGenerator {
	// volatile 不能保证递增为原子操作
	private static volatile int serialNumber = 0;

	public static int nextSerialNumber() {
		return serialNumber++; // Not thread-safe
	}
}
