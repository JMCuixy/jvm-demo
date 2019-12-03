package org.demo.jvm.aop.cglib;

import org.demo.jvm.aop.MyAspect;
import org.demo.jvm.aop.UserService;
import org.demo.jvm.aop.UserServiceImpl;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author : cuixiuyin
 * @date : 2019/12/3
 */
public class MyFactoryBean {

    private MyFactoryBean() {
    }

    public static UserService getInstance() {
        // target ： 目标类
        final UserService userService = new UserServiceImpl();
        // Aspect : 切面类
        final MyAspect myAspect = new MyAspect();
        // Weaving : 织入，也就是产生代理的过程
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(userService.getClass());
        enhancer.setUseCache(false);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                // 模拟 pointcut-切点
                if ("eat".equals(method.getName())) {
                    myAspect.before();
                }
                return methodProxy.invokeSuper(o, objects);
            }
        });
        return (UserService) enhancer.create();
    }

    public static void main(String[] args) {
        UserService proxyInstance = MyFactoryBean.getInstance();
        // 先洗手再吃东西
        proxyInstance.eat();
    }
}
