package com.me.thinkinginjava.chapter0_javabase.java_reflection;

import java.util.List;

/**
 * instanceof:�����ж�һ��������ʲô  ���� instanceof ��
 * 
 * @author BloodFly
 *
 */
public class Test_4_instanceof {

	public static void main(String[] args) {
		List<Employee> list = Company.getEmployees();
		for (Employee employee : list) {
			if (employee instanceof Manager) {
				employee.addSalary(5000);
			} else {
				employee.addSalary(3000);
			}
			System.out.println(employee.toString());
		}
		
		Manager manager = new Manager();
		if (manager instanceof Employee) {
			System.out.println("����Ҳ��Ա��");
		} else {
			System.out.println("������Ա��");
		}
	}

}
