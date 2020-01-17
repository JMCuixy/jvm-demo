package org.jvm.demo.chapter11;

/**
 * 公共子表达式消除
 *
 * @author : cuixiuyin
 * @date : 2020/1/16
 */
public class Test2 {

    public static void main(String[] args) {
        int a = 1;
        int b = 1;
        int c = 1;
        int d = (c * b) * 12 + a + (a + b * c);
        // 1. 提取公共子表达式
        int E = c * b;
        d = E * 12 + a + (a + E);
        // 2. 代数化简
        d = E * 13 + a * 2;
    }
}
