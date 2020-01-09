package org.jvm.demo.chapter2;

/**
 * @author : cuixiuyin
 * @date : 2019/11/13
 */
public class StrIntrenTest {

    /**
     * intern() 用法：返回常量池中的字符串引用。
     * 1. 如果常量池中已存在该字符串，则直接返回常量池中该对象的引用。
     * 2. 如果常量池中不存在该字符串，则在常量池中加入该对象引用并返回。
     *
     * @param args
     */
    public static void main(String[] args) {
        // 1. 字面量创建形式
        String s1 = "jmcui";
        //  1. 在字符串常量池中生成字符串【"jmcui"】实例
        //  2. 将栈中的 s1 指向字符串常量池中的字符串【"jmcui"】实例

        System.out.println("s1 == s1.intern() ：" + (s1 == s1.intern())); // true

        // 2. new 创建方式
        String s2 = new String("jmcui");
        //  1. 在Java堆中生成字符串【"jmcui"】实例
        //  2. 将栈中的 s2 指向Java堆中的字符串【"jmcui"】实例

        System.out.println("s1 == s2 ：" + (s1 == s2)); // false
        System.out.println("s1.equals(s2) ：" + s1.equals(s2)); // true
        System.out.println("s1 == s2.intern()：" + (s1 == s2.intern())); // true

        // 3. StringBuilder/StringBuffer 方式和 new 方法类似
        String s3 = new StringBuilder("jm").append("cui").toString();
        //  1. 在Java堆中生成字符串【"jmcui"】实例
        //  2. 将栈中的 s2 指向Java堆中的字符串【"jmcui"】实例
        System.out.println("s1 == s3 ：" + (s1 == s3)); // false
        System.out.println("s2 == s3 ：" + (s2 == s3)); // false
        System.out.println("s2.intern() == s3.intern() ：" + (s2.intern() == s3.intern())); // true
    }
}
