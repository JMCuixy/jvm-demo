package org.jvm.demo.chapter7;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author 1099442418@qq.com
 * @Date 2020/4/15 15:24
 * @Description 类加载器和 instanceof 的关系
 */
public class ClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        // 简单类加载器
        ClassLoader myClassLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream inputStream = getClass().getResourceAsStream(fileName);
                    if (inputStream == null) {
                        return super.loadClass(name);
                    }
                    byte[] bytes = new byte[inputStream.available()];
                    inputStream.read(bytes);
                    return defineClass(name, bytes, 0, bytes.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return super.loadClass(name);
            }
        };
        Object newInstance = myClassLoader.loadClass("org.jvm.demo.chapter7.ClassLoaderTest").newInstance();
        System.out.println(newInstance.getClass()); // org.jvm.demo.chapter7.ClassLoaderTest
        System.out.println(newInstance instanceof org.jvm.demo.chapter7.ClassLoaderTest); // false
    }
}
