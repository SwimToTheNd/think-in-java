package com.me.thinkinginjava.jvm;

import com.sun.crypto.provider.DESKeyFactory;

/**
 * ClassLoader
 * 启动类加载器：负责加载JRE的核心类库，如jre目标下的rt.jar,charsets.jar等
 * 扩展类加载器：负责加载JRE扩展目录ext中JAR类包
 * 系统类加载器：负责加载ClassPath路径下的类包
 * 用户自定义加载器：负责加载用户自定义路径下的类包
 * create by BloodFly at 2020/3/31
 */
public class C01_ClassLoader {
    public static void main(String[] args) {
        // BootstrapClassLoader
        System.out.println(String.class.getClassLoader());
        // ExtClassLoader
        System.out.println(DESKeyFactory.class.getClassLoader().getClass().getName());
        // AppClassLoader
        System.out.println(C01_ClassLoader.class.getClassLoader().getClass().getName());
        System.out.println(ClassLoader.getSystemClassLoader().getClass().getName());
    }
}
