package com.me.thinkinginjava.chapter0_javabase.java_generic;

/**
 * ���ڲ��Ժ��߽�ķ��ͷ��� {@link Sortor}
 * 
 * @author BloodFly
 *
 */
public class Test_07_Generic_Boundary_Mehtod {

	public static void main(String[] args) {
		Sortor sortor = new Sortor();
		int result = sortor.getMax(12, 22);
		System.out.println("�ú��߽�ķ��ͷ�����ȡ����ֵ��" + result);
	}

}
