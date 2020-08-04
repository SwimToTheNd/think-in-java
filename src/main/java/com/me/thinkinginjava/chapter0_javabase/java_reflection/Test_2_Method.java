package com.me.thinkinginjava.chapter0_javabase.java_reflection;

import java.lang.reflect.Method;

/**
 * ��ķ�����������Method
 * <p>
 * ͨ��Method��ȡ�����ķ���Ȩ�ޣ��������ͣ�����ֵ���͵�
 * <p>
 * ʹ�÷�������ȡ��ķ�����������Method����ȡ��ķ���
 * <p>
 * 1. Class.getMethod(String methodName, Class...parms) ָ���������Ͳ�����ȡ���еķ���
 * <p>
 * 2. Class.getMethods() ��ȡ���еĹ��з���
 * <p>
 * 3. Class.getDeclaredMethod(String methodName, Class...parms) ָ���������Ͳ�����ȡ����
 * public, protected, private
 * <p>
 * 4. Class.getDeclaredMethods(); ��ȡ���еķ���
 * 
 * @author BloodFly
 *
 */
public class Test_2_Method {

	public static void main(String[] args) {
		Class<?> clazz = Human.class;
		try {
			System.out.println("================Class.getMehtod()=======================================");
			// 1.ָ���������Ͳ�����ȡ���еķ���
			// public abstract
			Method method = clazz.getMethod("speak");
			System.out.println(method.toString());
			// public abstract
			method = clazz.getMethod("speak", String.class);
			System.out.println(method.toString());

			// protected ���ܹ�ͨ��getMethod��ȡ
			// method = clazz.getMethod("useTool");
			// System.out.println(method.toString());
			// private ���ܹ�ͨ��getMethod��ȡ
			// method = clazz.getMethod("useTool", String.class);
			// System.out.println(method.toString());

			// public
			method = clazz.getMethod("eat", String.class);
			System.out.println(method.toString());
			// public static
			method = clazz.getMethod("listen");
			System.out.println(method.toString());

			// protected abstract ����ͨ��getMethod��ȡ
			// method = clazz.getMethod("listen", String.class);
			// System.out.println(method.toString());

			// public final
			method = clazz.getMethod("fly");
			System.out.println(method.toString());
			// public native
			method = clazz.getMethod("think");
			System.out.println(method.toString());
			
			System.out.println("================Class.getMethods()=====================================");
			// 2. ��ȡ���еĹ��з��� getMethods()
			// �������и���Ĺ��з���
			Method[] methods = clazz.getMethods();
			for (Method met : methods) {
				System.out.println(met.toString());
			}
			
			System.out.println("================Class.getDeclaredMethod()=============================");
			// 3. ��ȡ�����ķ����������ǹ��еķ��� getDeclaredMethod(String name, Class<?>...parms)
			//    protected ���ܹ�ͨ��getMethod��ȡ
			method = clazz.getDeclaredMethod("useTool");
			System.out.println(method.toString());
			// private ���ܹ�ͨ��getMethod��ȡ
			method = clazz.getDeclaredMethod("useTool", String.class);
			System.out.println(method.toString());
			// protected abstract ����ͨ��getMethod��ȡ
			method = clazz.getDeclaredMethod("listen", String.class);
			System.out.println(method.toString());
			
			System.out.println("================Class.getDeclaredMethods()=============================");
			// 4. ��ȡ�����౾����ķ����������� getDeclaredMethods();
			//    ����������������ķ���
			Method[] method2 = clazz.getDeclaredMethods();
			for (Method met2 : method2) {
				System.out.println(met2.toString());
			}
			

		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

	}

}
