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
public class ObjectHeaderTest {

    public static void main(String[] args) {
        L l = new L();
        // 生成一对象的hashcode，会将写hashcode写入对象的头中
        System.out.println("hashcode = " + l.hashCode() + " 0x" + Integer.toHexString(l.hashCode()));
        // 打印l的对象布局
        System.out.println(ClassLayout.parseInstance(l).toPrintable());
        // 打印个数组的对象布局
//        int[] arr = new int[3];
//        System.out.println(ClassLayout.parseInstance(arr).toPrintable());
    }


}
