package com.me.thinkinginjava.chapter0_javabase.java_generic;

/**
 * ��������͵ĳ������ࣺʹ�ø���Ĳ����������̳�
 * 
 * @author BloodFly
 *
 */
public class Teacher2<T, S> extends Person<T> {
	protected T t;
	private S s;

	/**
	 * �븸�����������ͬ�Ĺ��캯��
	 * 
	 * @param t
	 */
	public Teacher2(T t) {
		// ʹ�ø���Ĺ��캯��
		super(t);
	}

	public void set(T t, S s) {
		this.t = t;
		this.s = s;
	}

}
