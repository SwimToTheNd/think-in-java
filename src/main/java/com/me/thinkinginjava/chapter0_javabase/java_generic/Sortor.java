package com.me.thinkinginjava.chapter0_javabase.java_generic;

/**
 * ���ں��߽�ķ��ͷ���
 * 
 * @author BloodFly
 *
 */
public class Sortor {

	/**
	 * ʹ�ú��б߽�ķ��ͷ���
	 * @param t1
	 * @param t2
	 * @return
	 */
	public <T extends Integer> T getMax(T t1, T t2) {
		if (t1 > t2) {
			return t1;
		} else {
			return t2;
		}
	}
}
