package org.demo.jvm.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author : cuixiuyin
 * @date : 2019/12/2
 */
public class UserInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("eat".equals(method.getName())) {
            System.out.println("先洗手");
        }
        Object invoke = method.invoke(proxy, args);
        return invoke;
    }

    public static void main(String[] args) {
        UserService userService = (UserService) Proxy.newProxyInstance(UserInvocationHandler.class.getClassLoader(),
                new Class[]{UserService.class}, new UserInvocationHandler());
        userService.eat();
    }
}
