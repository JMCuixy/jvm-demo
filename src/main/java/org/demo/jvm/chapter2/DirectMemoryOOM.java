package org.demo.jvm.chapter2;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 本机直接内存溢出（明显的特征是在 Heap Dump 文件中不会看见明显的异常）
 * <p>
 * VM Args: -Xmx20M -XX:MaxDirectMemorySize=10M
 *
 * @author : cuixiuyin
 * @date : 2019/11/17
 */
public class DirectMemoryOOM {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws IllegalAccessException, InterruptedException {
        Field field = Unsafe.class.getDeclaredFields()[0];
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        Thread.sleep(10000);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
