package org.jvm.demo.chapter7;

/**
 * @Author 1099442418@qq.com
 * @Date 2020/4/18 11:29
 * @Description 方法动态分配演示
 */
public class DynamicDispatch {

    static abstract class Human {
        protected abstract void sayHello();
    }

    static class Man extends Human {

        @Override
        protected void sayHello() {
            System.out.println("Man sayHello");
        }
    }

    static class Woman extends Human {

        @Override
        protected void sayHello() {
            System.out.println("Woman sayHello");
        }
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human women = new Woman();
        man.sayHello();
        women.sayHello();
        man = new Woman();
        man.sayHello();
    }
}
