package com.me.thinkinginjava.chapter0_javabase.java_io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * �ڵ��� �ֽ��� 
 * 1. FileInputStream extend InputStream
 * 2. �ֽ��� ���ֽڶ�ȡ��1���ַ�ռ�����ֽڣ�
 * 3. ��ȡȫ�ǣ������ַ�����ռ�����ֽڣ����ȡ����
 */
public class JeiDianLiu_FileInputStream {

	public static void main(String[] args) {
		int b = 0;
		long num = 0;
		FileInputStream fis = null;

		try {
			fis = new FileInputStream("E:/work/JAVAWEB/LernJavaBase/src/java_io/JeiDianLiu_FileInputStream.java");
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		try {
			while ((b = fis.read()) != -1) {
				System.out.print((char) b);
				num++;
			}
			fis.close();
			System.out.println();
			System.out.println("һ����ȡ�� " + num + "���ֽ�");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
