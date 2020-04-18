package org.jvm.demo.chapter7;

/**
 * @Author 1099442418@qq.com
 * @Date 2020/4/18 10:56
 * @Description 方法静态分配演示
 */
public class StaticDispatch {

    static abstract class Human {

    }

    static class Man extends Human {

    }

    static class Women extends Human {

    }

    public static void main(String[] args) {
        Human man = new Man();
        Human women = new Women();
        StaticDispatch staticDispatch = new StaticDispatch();
        staticDispatch.sayHello(man);
        staticDispatch.sayHello(women);
    }

    public void sayHello(Human human) {
        System.out.println("Hello Human");
    }

    public void sayHello(Man man) {
        System.out.println("Hello Man");
    }

    public void sayHello(Women women) {
        System.out.println("Hello Women");
    }
}
