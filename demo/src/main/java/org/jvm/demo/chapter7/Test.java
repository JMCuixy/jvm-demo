package org.jvm.demo.chapter7;

import sun.misc.Launcher;
import sun.plugin.extension.ExtensionUtils;

/**
 * @Author xiuyin.cui@luckincoffee.com
 * @Date 2020/4/15 15:48
 * @Description 1.0
 */
public class Test {

    public static void main(String[] args) {
        ClassLoader classLoader = Launcher.getLauncher().getClassLoader();

        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();

    }
}
