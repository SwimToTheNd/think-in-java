package com.me.thinkinginjava.chapter0_javabase.java_generic;

import java.awt.Button;
import java.util.Date;

/**
 * ���Է��ͷ���
 * @author BloodFly
 *
 */
public class Test_02_Generic_Mehtod {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		Factory factory = new Factory();
		// ʹ�÷��ͣ�����ʵ����һ��Date����
		Date date = factory.generate(Date.class);
		System.out.println(date.toString());
		// ʹ�÷��ͣ�����ʵ����һ��Button����
		Button button = factory.generate(Button.class);
		button.setName("�ύ");
		System.out.println(button.toString());
	}

}
