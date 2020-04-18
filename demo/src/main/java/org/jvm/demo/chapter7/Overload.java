package org.jvm.demo.chapter7;

import java.io.Serializable;

/**
 * @Author 1099442418@qq.com
 * @Date 2020/4/18 11:21
 * @Description 重载方法匹配优先级
 */
public class Overload {


    public static void main(String[] args) {
        sayHello('a');
    }

    private static void sayHello(char a) {
        System.out.println("char" + a);
    }

    private static void sayHello(Object a) {
        System.out.println("Object" + a);
    }

    private static void sayHello(int a) {
        System.out.println("int" + a);
    }

    private static void sayHello(long a) {
        System.out.println("long" + a);
    }

    private static void sayHello(float a) {
        System.out.println("float" + a);
    }

    private static void sayHello(double a) {
        System.out.println("double" + a);
    }

    private static void sayHello(Character a) {
        System.out.println("Character" + a);
    }

    private static void sayHello(Serializable a) {
        System.out.println("Serializable" + a);
    }

    private static void sayHello(char... a) {
        System.out.println("char..." + a);
    }
}
