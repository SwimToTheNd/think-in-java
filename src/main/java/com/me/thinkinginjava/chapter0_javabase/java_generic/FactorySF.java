package com.me.thinkinginjava.chapter0_javabase.java_generic;

/**
 * ���ж�߽�ķ�����
 * 
 * @author BloodFly
 *
 */
public class FactorySF<T extends Speakable & Flyable> {

	/**
	 * ���Ͳ���TΪͬʱ�̳���Speakable��Flyable������
	 * <p>
	 * ��˽�����:����javaû�ж�̳У����Կ�����Ϊʵ�ֽӿ�ҲΪ�̳У�
	 * 
	 * @param clazz
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public T create(Class<T> clazz) throws InstantiationException, IllegalAccessException {
		return clazz.newInstance();
	}
}
