package org.jvm.demo.chapter2;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 方法区用于存放 Class 的相关信息，如类名、访问修饰符、常量池、静态变量、字段描述、方法描述等，对于这个区域的测试基本思路都是运行时产生大量的类
 * 去填充方法区，直到溢出。
 * <p>
 * 在动态生成大量 Class 的应用中，需要特别注意类的回收情况。比较常见的场景有：Cglib 字节码增强和动态语言、大量JSP或动态产生JSP文件、基于 OSGi 的应用
 * <p>
 * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M（在 Java8 中该参数无效）
 *          -XX:MaxMetaspaceSize=10M（java8 将方法区放到了本地内存中）
 *
 * @author : cuixiuyin
 * @date : 2019/11/17
 */
public class JavaMethodAreaOOM {


    static class OOMObject {

    }

    public static void main(String[] args) {
        // Cglib 字节码技术，动态的增强类信息
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(o, objects);
                }

            });
            enhancer.create();
        }
    }
}
