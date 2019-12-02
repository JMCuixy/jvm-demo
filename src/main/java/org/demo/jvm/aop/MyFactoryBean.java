package org.demo.jvm.aop;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 产生代理对象的工厂类
 *
 * @author : cuixiuyin
 * @date : 2019/12/2
 */
public class MyFactoryBean {

    public static UserService getInstance() {
        // target ： 目标类
        final UserService userService = new UserServiceImpl();
        // Aspect : 切面类
        final MyAspect myAspect = new MyAspect();
        // Weaving : 织入，也就是产生代理的过程
        UserService proxyInstance = (UserService) Proxy.newProxyInstance(MyFactoryBean.class.getClassLoader(),
                new Class[]{UserService.class}, (Object proxy, Method method, Object[] args) -> {
                    if ("eat".equals(method.getName())) {
                        myAspect.before();
                    }
                    return method.invoke(userService, args);
                });
        return proxyInstance;
    }

    public static void main(String[] args) {
        UserService userService = MyFactoryBean.getInstance();
        userService.eat();
    }


}
