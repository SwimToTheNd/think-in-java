package com.me.thinkinginjava.chapter0_javabase.java_reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * ͨ�������޸Ķ������Ե�ֵ
 * Field.set(Object obj, Object args) obj Ҫ�޸ĵĶ��� args �����޸ĳ�..
 * @author BloodFly
 *
 */
public class Test_9_Field_set {

	public static void main(String[] args) {
		String className = "java_reflection.Teacher";
		try {
			Class<?> clazz = Class.forName(className);
			AbstractPerson person = (AbstractPerson) clazz.newInstance();
			Field field = clazz.getDeclaredField("position");
			Method method = clazz.getMethod("setName", String.class);
			method.invoke(person, "��С��");
			field.set(person, "Master");
			System.out.println(person.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
