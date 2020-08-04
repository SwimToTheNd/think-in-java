package com.me.thinkinginjava.chapter0_javabase.java_generic;

/**
 * �������ڽӿ�
 * ��������T���ڽӿ�
 * @author BloodFly
 *
 */
public interface InterfaceFactory<T> {

	/**
	 * �ӿڵķ��ͷ��������ڽ��ղ�ͬ�Ĳ�������
	 */
	public T create();
}
