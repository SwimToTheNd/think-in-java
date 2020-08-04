package com.me.thinkinginjava.chapter0_javabase.java_reflection;

/**
 * 1. һ����̳�һ���ӿڣ�������������ʵ�ֽӿ��е����з���(���󷽷�)��Ӧ�ý�������Ϊ������
 * <p>
 * 2. ���󷽷�ֻ��������������ʵ��
 * <p>
 * 3. һ����������г��󷽷�����ô�����ҲӦ������Ϊ������
 * <p>
 * 4. һ������ʹ��native���Σ���ʾʹ����������ʵ�֣�ֻ��������ʵ��
 * @author BloodFly
 *
 */
public abstract class Human implements Speakable{

	//δʵ�ֽӿڵĳ��󷽷����ඨ��Ϊ������
	
	protected void useTool(){
		
	};
	
	private void useTool(String toolName) {
		
	}
	
	public void eat(String food) {
		
	}
	
	public static void listen() {
		
	}
	
	protected abstract void listen(String destination);
	
	public final void fly() {
		
	}
	
	/**
	 * ʹ����������ʵ�ֵķ���
	 */
	public native void think();
}
