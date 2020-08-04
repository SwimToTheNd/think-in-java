package com.me.thinkinginjava.chapter0_javabase.java_reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * jdk动态代理调用处理器
 * 
 * @author BloodFly
 *
 */
public class MyInvocationHandler implements InvocationHandler {

	// 委托对象
	private Object proxyed;

	public MyInvocationHandler(Object proxyed) {
		this.proxyed = proxyed;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// doSomething
		System.out.println("当前系统时间" + System.currentTimeMillis());

		// 反射调用委托对象的方法
		method.invoke(proxyed, args);

		// doSomething
		return null;
	}

}
