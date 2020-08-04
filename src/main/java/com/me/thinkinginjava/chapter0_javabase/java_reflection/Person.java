package com.me.thinkinginjava.chapter0_javabase.java_reflection;

import java.util.Date;

public class Person {
	public String name;
	protected boolean sex;
	private int age;
	
	public Person() {

	}

	public Person(String name) {

	}

	public Person(String name, int age) {

	}

	protected Person(boolean sex) {

	}

	private Person(Date birthday) {

	}

	public void speak(String message) {
		System.out.println(message);
	}
	
}
