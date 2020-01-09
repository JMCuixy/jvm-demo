package org.jvm.demo.aop;

/**
 * 切面类
 *
 * @author : cuixiuyin
 * @date : 2019/12/2
 */
public class MyAspect {
    /**
     * 前置通知
     */
    public void before() {
        System.out.print("先洗手再");
    }
}
