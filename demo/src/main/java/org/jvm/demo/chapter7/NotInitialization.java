package org.jvm.demo.chapter7;

/**
 * @Author 1099442418@qq.com
 * @Date 2020/4/14 15:16
 * @Description 1.0
 */
public class NotInitialization {

    static {
        System.out.println("NotInitialization init");
    }

    public static void main(String[] args) {
        /**
         * 被动使用类字段演示一
         * 通过子类引用父类的静态字段，不会导致子类初始化。对于静态字段，只有直接定义这个字段的类才会被初始化
         */
        System.out.println(SubClass.value);
        /**
         * 被动使用类字段演示二
         * 通过数组定义来引用类，不会触发此类的初始化，会调用 "Lorg.jvm.demo.chapter7.SubClass" 类的初始化过程
         */
        SubClass[] subClasses = new SubClass[10];
        /**
         * 被动使用类字段演示三
         * 常量在编译阶段会存入调用类的常量池中，本质上没有直接引用到定义常量的类，因此不会触发定义常量的类的初始化
         */
        System.out.println(ConstClass.HELLO_WORLD);
    }
}
