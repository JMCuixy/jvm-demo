package org.jvm.demo.chapter7;

/**
 * @Author 1099442418@qq.com
 * @Date 2020/4/14 15:28
 * @Description 1.0
 */
public class ConstClass {

    static {
        System.out.println("ConstClass init");
    }

    public static final String HELLO_WORLD = "Hello world";
}
