package com.me.thinkinginjava.jvm;


/**
 * 当Eden区没有足够空间进行分配时，虚拟机将发起一次Minor GC.
 * GC期间虚拟机又发现allocation1无法存入Survior空间，所以只好通过 分配担保机制 把新生代的对象提前转移到老年代中去，
 * 老年代上的空间足够存放allocation1，所以不会出现Full GC。
 * 执行Minor GC后，后面分配的对象如果能够存在eden区的话，还是会在eden区分配内存
 */
public class GCTest {
	public static void main(String[] args) throws InterruptedException {
		byte[] allocation1, allocation2, allocation3, allocation4, allocation5, allocation6;
		allocation1 = new byte[30231 * 1024];
		allocation2 = new byte[900*1024];
		allocation3 = new byte[1000*1024];
        allocation4 = new byte[1000*1024];
        allocation5 = new byte[1000*1024];
        allocation6 = new byte[1000*1024];
        allocation6 = new byte[1000*1024]; // eden 100%
        allocation6 = new byte[1000*1024]; // 触发MinerGC
        allocation6 = new byte[1000*1024]; // 后面分配的对象如果能够存在eden区的话，还是会在eden区分配内存
//        Thread.sleep(Integer.MAX_VALUE);
	}
}
