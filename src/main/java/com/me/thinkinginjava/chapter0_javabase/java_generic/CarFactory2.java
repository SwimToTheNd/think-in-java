package com.me.thinkinginjava.chapter0_javabase.java_generic;

/**
 * 含边界的泛型类 <T extends Car>
 * 指明类型参数T为Car类或者是Car的子类
 * @author BloodFly
 *
 * @param <T>
 */
public class CarFactory2<T extends Car> {
	
	/**
	 * 创建一个Car类的子类的实例对象
	 * @param clazz
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public T create(Class<T> clazz) throws InstantiationException, IllegalAccessException {
		System.out.println("装载发动机！");
		System.out.println("装载座椅！");
		System.out.println("装载轮胎！");
		return clazz.newInstance();
	}
}
