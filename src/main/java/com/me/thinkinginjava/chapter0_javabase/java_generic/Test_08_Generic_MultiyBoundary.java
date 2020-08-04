package com.me.thinkinginjava.chapter0_javabase.java_generic;

/**
 * ���ڲ��Ժ��ж�߽�ķ�����{@link FactorySF} and {@link Parrot}
 * 
 * @author BloodFly
 *
 */
public class Test_08_Generic_MultiyBoundary {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		FactorySF<Parrot> factorySF = new FactorySF<Parrot>();
		Parrot parrot = factorySF.create(Parrot.class);
		System.out.println(parrot.speak());
	}

}
