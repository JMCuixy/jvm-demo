package org.jvm.demo.chapter4.btrace;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author : cuixiuyin
 * @date : 2020/3/26
 */
@Component
public class BtraceCase {

    static final Random random = new Random();

    public void run() throws InterruptedException {
        while (true) {
            int a = random.nextInt(100);
            int b = random.nextInt(100);
            int add = add(a, b);
            System.out.println("add:" + add);

        }
    }

    public int add(int a, int b) throws InterruptedException {
        Thread.sleep(1000);
        return a + b;
    }

    public static void main(String[] args) throws InterruptedException {
        new BtraceCase().run();
    }
}
