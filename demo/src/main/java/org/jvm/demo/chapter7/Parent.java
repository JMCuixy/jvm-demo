package org.jvm.demo.chapter7;

/**
 * @Author xiuyin.cui@luckincoffee.com
 * @Date 2020/4/15 9:42
 * @Description 1.0
 */
public class Parent {

    public static int A = 1;

    /**
     * 静态语句块中只能访问到定义在静态语句块之前的变量，定义在它之后的变量，在前面的静态语句块中可以赋值，但是不能访问。
     */
    static {
        A = 2;
        B = 3;
        //System.out.println(B);
    }

    public static int B = 2;

    static class Sub extends Parent {
        public static int C = A;
        public static int D = B;
    }

    public static void main(String[] args) {
        System.out.println(Sub.C);
        System.out.println(Sub.D);
    }
}
