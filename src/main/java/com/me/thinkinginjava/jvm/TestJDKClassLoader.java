package com.me.thinkinginjava.jvm;

/**
 * jdk类加载器
 */
public class TestJDKClassLoader {

    public static void main(String[] args) {
        // null BootstrapClassLoader
        System.out.println(String.class.getClassLoader());
        // ExtClassLoader
        System.out.println(com.sun.crypto.provider.DESKeyFactory.class.getClassLoader().getClass().getName());
        // AppClassLoader
        System.out.println(TestJDKClassLoader.class.getClassLoader().getClass().getName());
        // AppClassLoader
        System.out.println(ClassLoader.getSystemClassLoader().getClass().getName());
    }
}
