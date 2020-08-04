package com.me.thinkinginjava.chapter0_javabase.java_io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Test_Java_Util {

	/**
	 * ���һ����Class�ļ��ľ���·��
	 * @param pString:��·����
	 * @param className:����
	 * @return URI
	 */
	private URI getClassPathUri(String pString, String className){
		return null;
	}

	public String getMenue() {
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("===========================================================\n");
		sBuffer.append("��ѡ��IO���Թ��ܣ�\n");
		sBuffer.append("   1. �ڵ��ַ������� File:FileReader\n");
		sBuffer.append("   2. �ڵ��ַ������ File:FileWriter\n");
		sBuffer.append("   3. �ڵ��ַ������� Memory Array:CharArrayReader\n");
		sBuffer.append("   4. �ڵ��ַ������� Memory Array:CharArrayWriter\n");
		sBuffer.append("   5. �ڵ��ַ������� Memory String:StringReader\n");
		sBuffer.append("   6. �ڵ��ַ������ Memory String:StringWriter\n");
		sBuffer.append("   7. �ڵ��ֽ������� Pipe:PipedReader\n");
		sBuffer.append("   8. �ڵ��ֽ������ Pipe:PipedWriter\n");
		sBuffer.append("   9. �ڵ��ֽ������� File:FileInputStream\n");
		sBuffer.append("  10. �ڵ��ֽ������ File:FileOutputStream\n");
		sBuffer.append("  11. �ڵ��ֽ������� Memory Array:ByteArrayInputStream\n");
		sBuffer.append("  12. �ڵ��ֽ������ Memory Array:ByteArrayOutputStream\n");
		sBuffer.append("  13. �ڵ��ֽ������� Pipe:PipedRInputStream\n");
		sBuffer.append("  14. �ڵ��ֽ������ Pipe:PipedROutputStream\n");
		sBuffer.append("   0. �˳�����\n");
		sBuffer.append("===========================================================");
		return sBuffer.toString();
	}

	public void Test_FileReader() {
		URL urlString = null;
		String classpath = null;
		String classname = "java_io.Test_Java_IO";
		try {
			// ��ȡ�������ļ���·�����������Լ�
			urlString = Class.forName(classname).getResource("");
			// �õ����ǵ�ǰ��classpath�ľ���URI·����
			// urlString = Class.forName(classname).getResource("/");
			classpath = urlString.toString() + "Test_Java_IO.class";
			System.out.println(classpath);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			try {
				FileReader fileReader = new FileReader(new File(new URI(classpath)));
				int b = 0;
				try {
					while ((b = fileReader.read()) != 0) {
						System.out.println((char) b);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
	}

	public void Test_FileInputStream() {
	}
}
