package com.me.thinkinginjava.chapter0_javabase.java_reflection;

import java.lang.reflect.Field;

/**
 * ���������������Filed
 * <p>
 * ������ȡ�ֶ����ͣ�����Ȩ�޵���Ϣ
 * <p>
 * 1. getField(String fieldName); �����ڲ��Ѿ�������Ϊ����
 * <p>
 * 2. getFields(); ��ȡ���е��������ڲ�����Ĺ���Field����
 * <p>
 * 3. getDeclaredField(String fieldName); �����������������
 * <p>
 * 4. getDeclaredFields(); ���ж����������������
 * 
 * @author BloodFly
 *
 */
public class Test_3_Field {

	public static void main(String[] args) {
		Class<?> clazz = Person.class;
		try {
			// 1. getField(String fieldName); �����ڲ��Ѿ�������Ϊ����
			// public
			Field field = clazz.getField("name");
			System.out.println(field.toString());

			// protected ���ܹ���ȡ�ܱ�����Field��������
			// field = clazz.getField("sex");
			// System.out.println(field);

			// private ���ܹ���ȡ˽�е�Field��������
			// field = clazz.getField("age");
			// System.out.println(field);
			System.out.println("====================================================");

			// 2.getFields(); ��ȡ���е��������ڲ�����Ĺ���Field����
			Field[] fields = clazz.getFields();
			for (Field field2 : fields) {
				System.out.println(field2.toString());
			}
			System.out.println("====================================================");

			// 3. getDeclaredField(String fieldName); �����������������
			// public
			field = clazz.getDeclaredField("name");
			System.out.println(field.toString());
			// protected
			field = clazz.getDeclaredField("sex");
			System.out.println(field.toString());
			// private
			field = clazz.getDeclaredField("age");
			System.out.println(field.toString());
			System.out.println("====================================================");

			// 4. getDeclaredFields(); ���ж����������������
			fields = clazz.getDeclaredFields();
			for (Field field2 : fields) {
				System.out.println(field2.toString());
			}

		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
	}

}
