package com.me.thinkinginjava.chapter0_javabase.java_reflection;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * �ڲ���Ĳ��� �ڲ����Ϊ��
 * <li>��Ա�ڲ���
 * <li>�ֲ��ڲ���
 * <li>�����ڲ���
 * <li>��̬�ڲ��� �ڲ���ᱻ�����class�ļ������ⲿ��$�ڲ���.class����
 * 
 * @author BloodFly
 *
 */
public class Test_InnerClass {

	public static void main(String[] args) {

	}

	// ��Ա�ڲ���
	class InnerClass {

	}

	// ��̬�ڲ���
	static {
		class StaticClass {
			
		}
	}

	public void Function() {
		// �ֲ��ڲ���
		class LocalClass {

		}
	}

	// �����ڲ���
	public OuterClass Function2() {
		return new OuterClass() {
			int count = 5;

			public void fun() {
				System.out.println(count);
			}
		};
	}
}

// ƽ���ⲿ�ֻ࣬����һ��public��
class OuterClass {

}