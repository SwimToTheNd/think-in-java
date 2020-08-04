package com.me.thinkinginjava.chapter0_javabase.java_reflection;

public class Teacher extends AbstractPerson {
	public String position;
	private float salary;

	public Teacher() {
	}

	public Teacher(String name) {
		this.setName(name);
	}

	public void speak(String message) {
		System.out.println("Speak: " + message);
	}

	public String toString() {
		return "[Name:"+ this.getName()+" Position:" + position + " Salary:" + salary + "]";
	}

	private float getSalary() {
		return salary;
	}

}
