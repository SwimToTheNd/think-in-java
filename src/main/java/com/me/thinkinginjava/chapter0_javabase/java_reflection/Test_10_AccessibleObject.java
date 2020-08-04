package com.me.thinkinginjava.chapter0_javabase.java_reflection;

import java.awt.geom.FlatteningPathIterator;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Method,Field,Constructor�̳���AccessibleObject �̳з���setAccessible(boolean flag)
 * 
 * @author BloodFly
 *
 */
public class Test_10_AccessibleObject {

	public static void main(String[] args) {
		String classname = "java_reflection.Teacher";
		try {
			Class<?> clazz = Class.forName(classname);
			AbstractPerson person = (AbstractPerson) clazz.newInstance();
			Field field = clazz.getDeclaredField("salary");
			// �޸�������������ķ���Ȩ��
			field.setAccessible(true);
			field.set(person, 5500.50f);
			System.out.println(person.toString());
			// �޸ķ�����������ķ���Ȩ��
			Method method = clazz.getDeclaredMethod("getSalary");
			method.setAccessible(true);
			float salary = (float) method.invoke(person);
			System.out.println("This Person's salary is :" + salary);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
