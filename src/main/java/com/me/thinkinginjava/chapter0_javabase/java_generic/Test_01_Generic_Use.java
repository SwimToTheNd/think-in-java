package com.me.thinkinginjava.chapter0_javabase.java_generic;

/**
 * ����ʹ�÷�����
 * @author BloodFly
 *
 */
public class Test_01_Generic_Use {

	public static void main(String[] args) {
		// ʹ�÷����࣬��������TΪInteger
		Person<Integer> person = new Person<Integer>(10);
		System.out.println(person.toString());
	}

}
