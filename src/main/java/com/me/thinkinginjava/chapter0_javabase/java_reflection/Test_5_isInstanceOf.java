package com.me.thinkinginjava.chapter0_javabase.java_reflection;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class Test_5_isInstanceOf {

	public static void main(String[] args) {
		// ����Ա��
		List<Employee> list = Company.getEmployees();
		// ��HashMap����Ա������
		Counter.addEmployeeType(Manager.class);
		Counter.addEmployeeType(Worker.class);
		// ͳ�Ƹ�������Ա������
		for (Employee employee : list) {
			System.out.println(employee.getClass().getCanonicalName());
			Counter.count(employee);
		}
		System.out.println("=================================================================");
		// ����Ա�����͸���Map
		Iterator<Entry<Class<?>, Integer>> iterator = Counter.employeeMap.entrySet().iterator();
		while (iterator.hasNext()) {
//			System.out.println(iterator.next());
			Entry<Class<?>, Integer> entry = iterator.next();
			System.out.println("Ա�����ͣ�"+entry.getKey().getCanonicalName()+" ���� ��"+entry.getValue());
		}
	}

}
