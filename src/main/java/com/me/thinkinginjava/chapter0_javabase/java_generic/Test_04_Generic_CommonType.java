package com.me.thinkinginjava.chapter0_javabase.java_generic;

import java.util.Date;

/**
 * ����ͨ������ͣ���
 * 
 * @author BloodFly
 *
 */
public class Test_04_Generic_CommonType {

	public static void main(String[] args) {

		// Classʹ��ͨ�������, clazz���Դ����������
		// ͨ�������?����Integer
		Class<?> clazz = Integer.class;
		System.out.println(clazz.getCanonicalName());
		// ͨ������ͣ�����String
		clazz = String.class;
		System.out.println(clazz.getCanonicalName());
		// ͨ������ͣ�����Date
		clazz = Date.class;
		System.out.println(clazz.getCanonicalName());
		

	}

}
