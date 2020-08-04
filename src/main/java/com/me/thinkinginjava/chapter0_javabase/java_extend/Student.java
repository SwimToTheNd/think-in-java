package com.me.thinkinginjava.chapter0_javabase.java_extend;

public class Student extends Person {

	private String schoole;

	public Student(String schoole) {
		super();
		this.schoole = schoole;
	}
	
	public String toString() {
		return super.toString()+"\tѧУ�� "+schoole;
	}
}
