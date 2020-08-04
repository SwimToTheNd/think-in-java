package com.me.thinkinginjava.chapter0_javabase.java_reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * ͨ������ʵ����һ����
 * ���ַ�����
 * <p>
 * 1. ���������������Class.newInstance()   ʹ���޲ι��캯������ʵ����
 * <p>
 * 2. ��Ĺ��캯����������Constructor.newInstance(Class<?>...parms)  ʹ����Ĺ��캯������������ʵ����
 * @author BloodFly
 *
 */
public class Test_7_newInstance {

	public static void main(String[]args) {
		String classname = "java_reflection.Teacher";
		try {
			// 1. ���������������Class.newInstance()   ʹ���޲ι��캯������ʵ����
			System.out.println("=====ʹ��Class.newInstance()ʵ��������=================");
			Class<?> clazz = Class.forName(classname);
			try {
				AbstractPerson person = (AbstractPerson) clazz.newInstance();
				System.out.println(person.toString());
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			// 2. ��Ĺ��캯����������Constructor.newInstance(Class<?>...parms)  ʹ����Ĺ��캯������������ʵ����
			System.out.println("=====ʹ��Constructor.newInstance()ʵ��������=================");
			try {
				Constructor<?> constructor = clazz.getConstructor(String.class);
				AbstractPerson person;
				try {
					person = (AbstractPerson) constructor.newInstance("��С��");
					System.out.println(person.toString());
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					e.printStackTrace();
				}
			} catch (NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
