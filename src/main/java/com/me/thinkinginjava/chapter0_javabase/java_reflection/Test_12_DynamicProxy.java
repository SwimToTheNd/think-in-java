package com.me.thinkinginjava.chapter0_javabase.java_reflection;

import java.lang.reflect.Proxy;

/**
 * jdk动态代理
 * 
 * @author BloodFly
 *
 */
public class Test_12_DynamicProxy {

	public static void main(String[] args) {
		HumanKind humanKind = new HumanKind();
		Speakable speakable = (Speakable) Proxy.newProxyInstance(Speakable.class.getClassLoader(), 
				new Class[] { Speakable.class },
				new MyInvocationHandler(humanKind));
		speakable.speak("Lesson One!");
	}


}
