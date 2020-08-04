package com.me.thinkinginjava.chapter0_javabase.java_generic;

/**
 * CarFactory实现泛型接口InterfaceFactory<T>
 * 指明泛型接口中类型参数T的具体类型为Car
 * @author BloodFly
 * @param <T>
 *
 */
public class CarFactory implements InterfaceFactory<Car> {

	@Override
	public Car create() {
		System.out.println("====装载发动机！");
		System.out.println("====装载座椅！");
		System.out.println("====装载轮胎！");
		return new Car();
	}
}
