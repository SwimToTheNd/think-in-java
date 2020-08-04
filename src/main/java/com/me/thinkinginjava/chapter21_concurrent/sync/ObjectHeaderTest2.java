package com.me.thinkinginjava.chapter21_concurrent.sync;

import org.openjdk.jol.info.ClassLayout;

/**
 * Bit-format of an object header (most significant first, big endian layout below):
 * <p>
 * 32 bits:
 * --------
 * hash:25 ------------>| age:4    biased_lock:1 lock:2 (normal object)
 * JavaThread*:23 epoch:2 age:4    biased_lock:1 lock:2 (biased object)
 * size:32 ------------------------------------------>| (CMS free block)
 * PromotedObject*:29 ---------->| promo_bits:3 ----->| (CMS promoted object)
 * <p>
 * 64 bits:
 * --------
 * unused:25 hash:31 -->| unused:1   age:4    biased_lock:1 lock:2 (normal object)
 * JavaThread*:54 epoch:2 unused:1   age:4    biased_lock:1 lock:2 (biased object)
 * PromotedObject*:61 --------------------->| promo_bits:3 ----->| (CMS promoted object)
 * size:64 ----------------------------------------------------->| (CMS free block)
 * <p>
 * unused:25 hash:31 -->| cms_free:1 age:4    biased_lock:1 lock:2 (COOPs && normal object)
 * JavaThread*:54 epoch:2 cms_free:1 age:4    biased_lock:1 lock:2 (COOPs && biased object)
 * narrowOop:32 unused:24 cms_free:1 unused:4 promo_bits:3 ----->| (COOPs && CMS promoted object)
 * unused:21 size:35 -->| cms_free:1 unused:7 ------------------>| (COOPs && CMS free block)
 * create by BloodFly at 2020/3/15
 */
public class ObjectHeaderTest2 implements Runnable {

    private static int i;
    private L l;

    public ObjectHeaderTest2(L l) {
        this.l = l;
    }

    public void add() {
        synchronized (l) {
            i++;
            // 打印hashcode
            System.out.println("hashcode: 0x" + Integer.toHexString(l.hashCode()));
            // 打印对象l的布局
            System.out.println(ClassLayout.parseInstance(l).toPrintable());
        }
        // 打印对象l的布局
        System.out.println(ClassLayout.parseInstance(l).toPrintable());
    }

    @Override
    public void run() {
        for (int j = 0; j < 100; j++) {
            add();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        L l = new L();
        Thread thread1 = new Thread(new ObjectHeaderTest2(l));
        Thread thread2 = new Thread(new ObjectHeaderTest2(l));
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("l address: " + l);
        System.out.println("l class: " + l.getClass());
        System.out.println("result: " + i);
    }


}
