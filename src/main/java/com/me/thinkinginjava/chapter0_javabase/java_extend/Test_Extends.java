package com.me.thinkinginjava.chapter0_javabase.java_extend;

public class Test_Extends {

	public static void main(String[] args) {

		Person p = new Person();
		p.setName("李  刚");
		p.setAge(22);
		p.setSex("男");
		System.out.println(p);

		Person person = new Student("重庆理工大学");
		person.setName("韩  立");
		person.setAge(21);
		person.setSex("男");
		System.out.println(person);

		Student student = new Student("重庆大学");
		student.setName("厉飞雨");
		student.setSex("男");
		student.setAge(22);
		System.out.println(student);


	}

}
