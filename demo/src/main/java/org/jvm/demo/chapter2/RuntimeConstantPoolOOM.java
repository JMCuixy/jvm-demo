package org.jvm.demo.chapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * 方法区和运行时常量池溢出
 * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M（在 Java8 中该参数无效）
 *          -XX:MaxMetaspaceSize=10M（java8 将方法区放到了本地内存中）
 *
 * @author : cuixiuyin
 * @date : 2019/11/13
 */
public class RuntimeConstantPoolOOM {


    public static void main(String[] args) {
        // 使用 List 保持常量池引用
        List<String> list = new ArrayList<>();
        // 10M的 PermSize 在 integer 范围内足够产生 OOM 了
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
