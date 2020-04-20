package org.jvm.demo.chapter9;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author 1099442418@qq.com
 * @Date 2020-04-20 9:51
 * @Description 1.0
 */
public class DynamicProxyTest {

    interface IHello {
        void sayHello();
    }

    static class Hello implements IHello {

        @Override
        public void sayHello() {
            System.out.println("Hello World");
        }
    }

    static class DynamicProxy implements InvocationHandler {
        Object originObj;

        Object bind(Object originObj) {
            this.originObj = originObj;
            Object proxyInstance = Proxy.newProxyInstance(originObj.getClass().getClassLoader(),
                    originObj.getClass().getInterfaces(), this);
            return proxyInstance;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("welcome");
            return method.invoke(originObj, args);
        }
    }

    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        IHello iHello = (IHello) new DynamicProxy().bind(new Hello());
        iHello.sayHello();
    }
}
