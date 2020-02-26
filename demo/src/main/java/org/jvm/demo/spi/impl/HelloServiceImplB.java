package org.jvm.demo.spi.impl;

import org.jvm.demo.spi.HelloService;

/**
 * @author : cuixiuyin
 * @date : 2020/2/25
 */
public class HelloServiceImplB implements HelloService {

    @Override
    public void hello() {
        System.out.println("Hello! I am ImplB");
    }
}
