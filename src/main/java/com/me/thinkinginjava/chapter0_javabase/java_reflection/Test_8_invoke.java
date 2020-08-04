package com.me.thinkinginjava.chapter0_javabase.java_reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ͨ����ķ���ִ�з���
 * <p>
 * Mehtod.inovke(Object obj, Object...args)
 * @author BloodFly
 *
 */
public class Test_8_invoke {

	public static void main(String[] args) {
		String classname = "java_reflection.Teacher";
		try {
			Class<?> clazzClass = Class.forName(classname);
			try {
				AbstractPerson person = (AbstractPerson) clazzClass.newInstance();
				try {
					Method method = clazzClass.getMethod("speak", String.class);
					try {
						method.invoke(person, "Lesson One!");
					} catch (IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
					}
				} catch (NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
