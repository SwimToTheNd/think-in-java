package com.me.thinkinginjava.chapter0_javabase.java_generic;

/**
 * ĩʹ�ø���Ĳ����������̳� �̳з�������Person ���Ͳ������ͣ� V��S
 * 
 * @author BloodFly
 *
 */
public class Teacher<V, S> extends Person {

	private V v;
	private S s;

	public Teacher(Object t) {
		super(t);
	}

	public void set(V v, S s) {
		this.v = v;
		this.s = s;
	}
}
