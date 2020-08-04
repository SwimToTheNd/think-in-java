package com.me.thinkinginjava.jvm;

/**
 * JVM加载jar包是否会将包里的所有类全部加载进内存？
 * JVM对class文件是按需加载(运行期间动态加载)，非一次性加载，见示例(启动需要加上参数：-verbose:class)
 * create by BloodFly at 2020/3/31
 */
public class C02_DynamicLoad {

    static {
        System.out.println("C02_DynamicLoad 执行静态块");
    }

    public C02_DynamicLoad() {
        System.out.println("执行 C02_DynamicLoad 构造方法");
    }

    public static void main(String[] args) {
        new A();
        System.out.println("main execute");
        new B();
    }

    private static class A {
        static {
            System.out.println("A 执行静态块");
        }

        public A() {
            System.out.println("执行 A 构造方法");
        }
    }

    private static class B {
        static {
            System.out.println("B 执行静态块");
        }

        public B() {
            System.out.println("执行 B 构造方法");
        }
    }
}
