package com.me.thinkinginjava.chapter0_javabase.java_facetest;

/**
 * ���Ϊ��ӡCall finally��Call MyException1����ѭ��
 * @author BloodFly
 *
 */
public class Test_01_Throws {

	public static void main(String[] args) {
		int i = 0;
		while (true) {
			try {
				if (i++ == 1) {
					throw new MyException1();
				} else if (i == 2) {
					throw new MyException2();
				}
			} catch (MyException1 e1) {
				System.out.println("Call MyException1");
			} catch (MyException2 e2) {
				System.out.println("Call MyException2");
			}finally {
				System.out.println("Call finally");
			}
		}
	}
}

class MyException1 extends Exception {

}

class MyException2 extends Exception {

}