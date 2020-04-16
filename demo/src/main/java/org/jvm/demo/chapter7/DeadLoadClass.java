package org.jvm.demo.chapter7;

/**
 * @Author xiuyin.cui@luckincoffee.com
 * @Date 2020/4/15 15:15
 * @Description
 */
public class DeadLoadClass {

    static {
        // 如果不加上 if 语句，编译器将拒绝编译
        if (true) {
            System.out.println(Thread.currentThread() + "DeadLoadClass init");
            while (true) {

            }
        }
    }

    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread() + "start");
            new DeadLoadClass();
            System.out.println(Thread.currentThread() + "run over");
        };
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
    }
}
