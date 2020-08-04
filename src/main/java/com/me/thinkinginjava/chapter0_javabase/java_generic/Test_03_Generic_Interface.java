package com.me.thinkinginjava.chapter0_javabase.java_generic;

/**
 * ���ڲ��Է��ͽӿ�
 * 
 * @author BloodFly
 *
 */
public class Test_03_Generic_Interface {

	public static void main(String[] args) {
		
		InterfaceFactory<Car> carFactory = new CarFactory();
		InterfaceFactory<Computer> computerFactory = new ComputerFactory();
		System.out.println("===��ʼ��������===");
		carFactory.create();
		System.out.println("===�����������===");
		System.out.println("===��ʼ��������===");
		computerFactory.create();
		System.out.println("===�����������===");
	}
}
