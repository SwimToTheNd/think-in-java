package com.me.thinkinginjava.jvm;

/**
 * 动态加载，类到运行时才加载
 */
public class TestDynamicLoad {

    static {
        System.out.println("*************static code************");
    }

    public static void main(String[] args){
        new A();
        System.out.println("*************load test************");
        new B();
    }
}

class A{
    public A(){
        System.out.println("*************initial A************");
    }
}

class B{
    public B(){
        System.out.println("*************initial B************");
    }
}
