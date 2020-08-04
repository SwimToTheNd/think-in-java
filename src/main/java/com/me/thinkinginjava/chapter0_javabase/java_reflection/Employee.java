package com.me.thinkinginjava.chapter0_javabase.java_reflection;

/**
 * Ա����
 * @author BloodFly
 *
 */
public abstract class Employee {

	protected int salary;

	public void addSalary(int amount) {
		this.salary += amount;
	}

	public abstract String toString();
}
