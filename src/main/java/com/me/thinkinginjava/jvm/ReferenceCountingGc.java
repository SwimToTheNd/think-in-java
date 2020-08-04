package com.me.thinkinginjava.jvm;

/**
 * 判断对象可以被回收：引用计数法
 * 给对象中添加一个引用计数器，每当有一个地方引用它，计数器就加1；当引用失效，计数器就减1；任何时候计数器为0的对象就是不可能再被使用的。
 * 这个方法实现简单，效率高，但是目前主流的虚拟机中并没有选择这个算法来管理内存，其最主要的原因是它很难解决对象之间相互循环引用的问题
 */
public class ReferenceCountingGc {
	Object instance = null;

	public static void main(String[] args) {
		ReferenceCountingGc objA = new ReferenceCountingGc();
		ReferenceCountingGc objB = new ReferenceCountingGc();
		objA.instance = objB;
		objB.instance = objA;
		objA = null;  // ReferenceCountingGc对象引用计数器不为1
		objB = null;
	}
}
