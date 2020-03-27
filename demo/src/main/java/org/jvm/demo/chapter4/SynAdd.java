package org.jvm.demo.chapter4;

/**
 * 模拟死锁代码样例
 *
 * @author : cuixiuyin
 * @date : 2020/3/27
 */
public class SynAdd implements Runnable {
    int a, b;

    public SynAdd(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        synchronized (Integer.valueOf(a)) {
            synchronized (Integer.valueOf(b)) {
                System.out.println(a + b);
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new SynAdd(1, 2)).start();
            new Thread(new SynAdd(2, 1)).start();
        }
    }
}
