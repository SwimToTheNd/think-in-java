package com.me.thinkinginjava.chapter0_javabase.java_reflection;

import java.util.HashMap;
import java.util.Map;

/**
 * ����ͳ�ƹ�˾�ڸ�������Ա���ĸ����� Class.isInstanceof(Object obj) �������ж�һ�������ǲ��������Ķ���
 * 
 * @author BloodFly
 *
 */
public class Counter {

	/**
	 * Key:Ա������ Value:Ա�����͵ĸ���
	 */
	public static Map<Class<?>, Integer> employeeMap = new HashMap<Class<?>, Integer>();

	/**
	 * �ж�һ��Ա��������
	 * <p>
	 * Manager.isInstanceof(����)
	 * <p>
	 * Worker.isInstanceof(����)
	 * 
	 * @param employee
	 */
	public static void count(Employee employee) {
		for (Class<?> clazz : employeeMap.keySet()) {
			if (clazz.isInstance(employee)) {
				employeeMap.put(clazz, employeeMap.get(clazz) + 1);
			}
		}
	}
	
	/**
	 * ���Ա������
	 * @param clazz
	 */
	public static void addEmployeeType(Class<?> clazz) {
		employeeMap.put(clazz, 0);
	}
	
	/**
	 * �Ƴ�Ա������
	 * @param clazz
	 */
	public static void removeEmployeeType(Class<?> clazz) {
		employeeMap.remove(clazz);
	}
}
