package com.me.thinkinginjava.chapter0_javabase.java_generic;

/**
 * ����
 * <p>
 * 1. ���� class ClassName{@code<T>} ��������{@code<T>}
 * 
 * @author BloodFly
 *
 */
public class Person<T> {

	private T t;

	public Person(T t) {
		this.t = t;
	}

	@Override
	public String toString() {
		// ͨ�������÷��Ͳ�������T������
		return "�����������ǣ�" + t.getClass().getCanonicalName();
	}
}
