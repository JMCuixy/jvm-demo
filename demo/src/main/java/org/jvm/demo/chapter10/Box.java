package org.jvm.demo.chapter10;

import lombok.Data;

/**
 * @author : cuixiuyin
 * @date : 2019/12/24
 */
@Data
public class Box<T> {//这里可以定义多个泛型，用逗号分割

    private String name;
    private T t;

    /**
     * 泛型继承
     */
    public static class tircleBox extends Box<String> {

    }

    public static class SquareBox extends Box<Integer> {

    }
}
