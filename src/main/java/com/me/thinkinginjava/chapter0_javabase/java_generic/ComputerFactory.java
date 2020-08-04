package com.me.thinkinginjava.chapter0_javabase.java_generic;

/**
 * CoumputerFactory实现泛型接口InterfaceFactory<T>
 * 使用ComputerFactory为一个具体类型的创建工厂
 * 指明类型参数T为具体类型Computer
 * @author BloodFly
 *
 */
public class ComputerFactory implements InterfaceFactory<Computer> {

	@Override
	public Computer create() {
		System.out.println("===装载主板！");
		System.out.println("===装载CPU！");
		System.out.println("===装载内存！");
		return new Computer();
	}

}
