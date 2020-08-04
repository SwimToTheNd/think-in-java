package com.me.thinkinginjava.chapter0_javabase.java_reflection;

/**
 * ʹ��Class.forName(String name) ����ʾ������ name:���ȫ�� ��.����
 * @author BloodFly
 *
 */
public class Test_6_forName {

	public static void main(String[] args) {
		System.out.println("��ʼ�����࣡");
		try {
			Class<?> clazz = Class.forName("java_reflection.Teahcer");
			System.out.println(clazz.getCanonicalName());
			System.out.println("������ɣ�");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
