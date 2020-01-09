package org.jvm.demo.chapter3;

/**
 * 引用计数算法的循环引用问题
 *
 * @author : cuixiuyin
 * @date : 2019/12/14
 */
public class ReferenceCountingGC {

    public Object instance = null;

    private static final int _1MB = 1024 * 1024;

    /**
     * 这个成员属性的唯一意义就是占点内存，以便能在 GC 日志中看清楚是否被回收过
     * todo: 为什么看不到这个 2048KB 的内存占用
     */
    private byte[] bigSize = new byte[2 * _1MB];

    public static void testGC() {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;

        //假设在这行发生 GC，objA 和 objB 是否能被回收。
        System.gc();
    }

    /**
     * -XX:+PrintGCDetails 打印 GC 日志
     */
    public static void main(String[] args) {
        testGC();
    }
}
