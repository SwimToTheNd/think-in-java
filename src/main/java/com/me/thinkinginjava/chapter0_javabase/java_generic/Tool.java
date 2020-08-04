package com.me.thinkinginjava.chapter0_javabase.java_generic;

import java.util.List;

/**
 * ͨ������Ĳ��� ��->T
 * 
 * @author BloodFly
 *
 */
public class Tool {
	
	public void exchange(List<?> list, int i, int j) {
		exchangeT(list, i, j);
	}
	
	/**
	 * ���ڲ���ͨ�����
	 * @param list
	 * @param i
	 * @param j
	 */
	private <T> void exchangeT(List<T> list, int i, int j) {
		// T����ͨ�����
		T t = list.get(i);
		list.set(i, list.get(j));
		list.set(j, t);
	}
}
