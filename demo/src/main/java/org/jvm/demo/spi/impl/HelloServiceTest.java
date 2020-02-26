package org.jvm.demo.spi.impl;

import org.jvm.demo.spi.HelloService;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author : cuixiuyin
 * @date : 2020/2/25
 */
public class HelloServiceTest {

    public static void main(String[] args) {
        ServiceLoader<HelloService> serviceLoader = ServiceLoader.load(HelloService.class);
        Iterator<HelloService> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            HelloService helloService = iterator.next();
            helloService.hello();
        }
    }
}
