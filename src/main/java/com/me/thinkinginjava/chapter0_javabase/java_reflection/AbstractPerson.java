package com.me.thinkinginjava.chapter0_javabase.java_reflection;

public abstract class AbstractPerson {
	private String name;

	public AbstractPerson() {
	}

	public AbstractPerson(String name) {
		this.name = name;
	}

	public abstract String toString();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
