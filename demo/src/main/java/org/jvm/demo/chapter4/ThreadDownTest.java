package org.jvm.demo.chapter4;

/**
 * @author : cuixiuyin
 * @date : 2020/3/16
 */
public class ThreadDownTest {

    /**
     * 线程死循环演示
     */
    public static void createBusyThread() {
        Thread thread = new Thread(() -> {
            while (true) ;
        }, "testBusyThread");
        thread.start();
    }

    /**
     * 线程锁等待演示
     */
}
