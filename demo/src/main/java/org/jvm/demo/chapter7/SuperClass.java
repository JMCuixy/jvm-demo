package org.jvm.demo.chapter7;

/**
 * @Author xiuyin.cui@luckincoffee.com
 * @Date 2020/4/14 15:12
 * @Description
 */
public class SuperClass {

    static {
        System.out.println("SuperClass init");
    }

    public static int value = 123;
}
