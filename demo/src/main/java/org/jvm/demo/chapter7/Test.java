package org.jvm.demo.chapter7;

import sun.misc.Launcher;

/**
 * @Author 1099442418@qq.com
 * @Date 2020/4/15 15:48
 * @Description 方法静态解析演示
 */
public class Test {

    public static void main(String[] args) {
        ClassLoader classLoader = Launcher.getLauncher().getClassLoader();

        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();

    }
}
