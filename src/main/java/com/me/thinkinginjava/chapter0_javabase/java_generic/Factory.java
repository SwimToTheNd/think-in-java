package com.me.thinkinginjava.chapter0_javabase.java_generic;

/**
 * 泛型方法的声明
 * public <T> T methodName(T t)
 * 1. {@code<T>}声明类型参数T
 * 2. T声明返回值类型为参数类型T
 * @author BloodFly
 *
 */
public class Factory {

	/**
	 * 使用泛型方法，利用反射返回一个类的实例
	 * @param t
	 * @return
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public <T> T generate(Class<T> t) throws InstantiationException, IllegalAccessException {
		return t.newInstance();
	}
}
