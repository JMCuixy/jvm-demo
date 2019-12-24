package org.demo.jvm.chapter10;

import java.util.Arrays;
import java.util.List;

/**
 * @author : cuixiuyin
 * @date : 2019/12/24
 */
public class Grammar {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        int sum = 0;
        for (int i : list) {
            sum += i;
        }
        System.out.println(sum);
        // 测试包装类的自动装箱和拆箱
        box();
    }

    public static void variableParam(String... strings) {

    }

    public static void box() {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;

        System.out.println("c == d：" + (c == d));
        System.out.println("e == f：" + (e == f));
        System.out.println("c == (a + b)：" + (c == (a + b)));
        System.out.println("c.equals(a + b)：" + (c.equals(a + b)));
        System.out.println("g == (a + b)：" + (g == (a + b)));
        System.out.println("g.equals(a + b)：" + (g.equals(a + b)));
    }
}
