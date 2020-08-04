package com.me.thinkinginjava.chapter0_javabase.java_reflection;

import java.lang.reflect.Constructor;
import java.util.Date;

/**
 * ????????????????Constructor
 * <p>
 * ??????????????????????
 * <p>
 * ??ù?????????????Constructor????????????? 1. Class.getConstuctor(Class<?>...args)
 * ???public?????? (???й???) 2. Class.getConstuctors(); ??????е?public?????? 3.
 * Class.getDeclaredConstructor(Class<?>...args);
 * ???????????public,private,protected,???? 4. Class.getDeclaredConstructors();
 * ??????е??????
 * 
 * @author BloodFly
 *
 */
public class Test_1_Constructor {

	public static void main(String[] args) {

		Class<?> clazz = Person.class;
		try {
			// 1. ?????ι?????
			Constructor<?> constructor = clazz.getConstructor();
			System.out.println(constructor.toString());

			// 2. ????????????????
			constructor = clazz.getConstructor(String.class);
			System.out.println(constructor.toString());
			constructor = clazz.getConstructor(String.class, int.class);
			System.out.println(constructor.toString());
			// protected??????????????
			// constructor4 = clazz.getConstructor(boolean.class);
			// System.out.println(constructor4.toString());
			// private??????????????
			// constructor5 = clazz.getConstructor(Date.class);
			// System.out.println(constructor5.toString());
			System.out.println("=============================================================");

			// 3. ??????е???й?????
			Constructor<?>[] constructors = clazz.getConstructors();
			for (Constructor<?> cons : constructors) {
				System.out.println(cons.toString());
			}
			System.out.println("=============================================================");

			// 4. ??????????????(public,private,protected)
			constructor = clazz.getDeclaredConstructor();
			System.out.println(constructor.toString());
			constructor = clazz.getDeclaredConstructor(String.class);
			System.out.println(constructor.toString());
			constructor = clazz.getDeclaredConstructor(String.class, int.class);
			System.out.println(constructor.toString());
			// protected
			constructor = clazz.getDeclaredConstructor(boolean.class);
			System.out.println(constructor.toString());
			// private
			constructor = clazz.getDeclaredConstructor(Date.class);
			System.out.println(constructor.toString());
			System.out.println("=============================================================");

			// 5. ??????????????????
			Constructor<?>[] constructors2 = clazz.getDeclaredConstructors();
			for (Constructor<?> cons2 : constructors2) {
				System.out.println(cons2.toString());
			}

		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
