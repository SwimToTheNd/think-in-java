package com.me.thinkinginjava.chapter0_javabase.java_reflection;

public class Test_11_HumanKindProxy {

	public static void main(String[] args) {
		HumanKind humanKind = new HumanKind();
		HumanKindProxy humanKindProxy = new HumanKindProxy(humanKind);
		humanKindProxy.speak("Hello World!");
	}

}
