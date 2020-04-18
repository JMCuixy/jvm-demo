package org.jvm.demo.chapter7;

import com.sun.jdi.PathSearchingVirtualMachine;

/**
 * @Author 1099442418@qq.com
 * @Date 2020/4/18 10:39
 * @Description 1.0
 */
public class StaticResolution {


    public static void main(String[] args) {
        sayHello();
    }

    private static void sayHello() {
        System.out.println("Hello World");
    }
}
