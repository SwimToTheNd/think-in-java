package com.me.thinkinginjava.chapter0_javabase.java_extend;

public class Person {
	private String name;
	private String sex;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * 私有的方法不会被子类继承
	 * @return void
	 */
	private void eat() {
		System.out.println("人吃饭！");
	}

	public String toString() {
		return "姓名：" + this.name + "\t年龄：" + this.age + "\t性别：" + this.sex;
	}
}