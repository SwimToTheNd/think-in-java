package com.me.thinkinginjava.chapter0_javabase.java_reflection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * ��˾��
 * @author BloodFly
 *
 */
public class Company {
	
	/**
	 * ���Ա������
	 * @return
	 */
	public static List<Employee> getEmployees() {
		List<Employee> employees = new ArrayList<Employee>();
		Random random = new Random();
		for (int i = 0; i < 5; i++) {
			if (random.nextInt(5)>3) {
				employees.add(new Manager());
			} else {
				employees.add(new Worker());
			}
		}
		
		return employees;
	}
}
