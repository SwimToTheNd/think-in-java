package com.me.thinkinginjava.chapter0_javabase.java_reflection;

import java.util.Arrays;
import java.util.List;

/**
 * ��������ࣺClass ������������ʱ���ӿڵ���Ϣ 1.
 * java��ö����һ���࣬����Ҳ������һ���࣬ע�ⱻ����Ϊ�ӿڡ�������ͬԪ�����ͺ�ά�������鹲��һ��Class���󡣿���ͨ��Class�����ȡ������
 * �������Ϣ ͨ��Class���ȡ���ԣ����췽�������������� 2. a. ͨ��Class.forName(String className) ��װ����
 * Class.forName(String className, boolean initialize, ClassLoader
 * loader)ȫ�������Ƿ��ʼ����������� b. ͨ��ClassLoader.loadClass()��װ����
 * 
 * @author BloodFly
 *
 */
public class Test_Class {

	public static void main(String[] args) {
		Class<Person> clazz = Person.class;
		// 1. ��.Class ͨ����������������������Class
		// ��ȡ��Ĺ淶������ ��.����
		System.out.println(clazz.getCanonicalName());

		// 2. ����.getClass() ͨ�����ʵ����getClass������ȡ�������������Class
		Person person = new Person();
		Class<?> clazz2 = person.getClass();
		System.out.println(clazz2.getCanonicalName());

		// ʹ��ʵ����getClass()���ж����ĸ���
		List<Person> list = Arrays.asList(new Teacherr(), new Student());
		for (Person p : list) {
			if (p.getClass().equals(Teacherr.class)) {
				System.out.println("This Person is a " + Teacherr.class.getCanonicalName());
				p.speak("I am a Teahcer!");
			} else {
				System.out.println("This Person is a " + Student.class.getCanonicalName());
				p.speak("I am a Student!");
			}
		}

		// 3. ʹ��Class.forName(String classname)����ʽ������(ʹ��ȫ����)
		String className = "java_reflection.Teacher";
		try {
			Class<?> class1 = Class.forName(className);
			System.out.println(class1.getCanonicalName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// 4. ʵ����һ���ӿ�ʱ��д���нӿ��еķ���
		Speakable speakable = new Speakable() {
			
			@Override
			public void speak(String message) {
				
			}
			
			@Override
			public void speak() {
				
			}
		};
	}

}


class Teacherr extends Person {

}

class Student extends Person {

}