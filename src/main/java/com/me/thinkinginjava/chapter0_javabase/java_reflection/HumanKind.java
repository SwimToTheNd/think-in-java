package com.me.thinkinginjava.chapter0_javabase.java_reflection;

public class HumanKind implements Speakable {

	@Override
	public void speak() {

	}

	@Override
	public void speak(String message) {
		System.out.println("Speak: " + message);
	}

}
