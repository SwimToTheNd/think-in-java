package com.me.thinkinginjava.chapter0_javabase.java_reflection;

/**
 * HumanKind������
 * 
 * @author BloodFly
 *
 */
public class HumanKindProxy implements Speakable {
	private HumanKind humanKind;

	public HumanKindProxy(HumanKind humanKind) {
		this.humanKind = humanKind;
	}

	@Override
	public void speak() {

	}

	@Override
	public void speak(String message) {
		System.out.println("����ʱ�䣺" + System.currentTimeMillis());
		this.humanKind.speak(message);
	}

}
