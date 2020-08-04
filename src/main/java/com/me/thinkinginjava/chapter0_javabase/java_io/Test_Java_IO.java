package com.me.thinkinginjava.chapter0_javabase.java_io;

import java.awt.Menu;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.LinkedList;

/*
 * java �������������
 */
public class Test_Java_IO {

	public static void main(String[] args) {

		int opt = 0;
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		Test_Java_Util tJava_Util = new Test_Java_Util();
		String menue = tJava_Util.getMenue();
		try {
			while (true) {
				System.out.println(menue);
				opt = Integer.parseInt(bReader.readLine());
				switch (opt) {
				case 0:
					System.exit(0);
				case 1:
					tJava_Util.Test_FileReader();
					break;

				default:
					break;
				}
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
}
