package com.me.thinkinginjava.chapter0_javabase.java_generic;

import java.util.ArrayList;
import java.util.List;

/**
 * ���Բ���ͨ������
 * 
 * @author BloodFly
 *
 */
public class Test_05_Generic_CatchCommonType {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("Hello");
		list.add(" ");
		list.add("World");
		System.out.println(list.toString());
		Tool tool = new Tool();
		// Tool��ʹ��exchangeT��List<T> ������List<?>,
		// ����T������ͨ�����
		tool.exchange(list, 0, 2);
		System.out.println(list.toString());
	}

}
