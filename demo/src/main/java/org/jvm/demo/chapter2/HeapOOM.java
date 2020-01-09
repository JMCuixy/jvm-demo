package org.jvm.demo.chapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * Java堆 溢出
 * <p>
 * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * -Xms20m 设置堆的最小值
 * -Xmx20m 设置堆的最大值 （-Xms和-Xmx设置成一样可以避免堆自动扩展）
 * -XX:+HeapDumpOnOutOfMemoryError 出现内存溢出异常时 Dump 出当前的内存堆转储快照
 *
 * @author : cuixiuyin
 * @date : 2019/11/12
 */
public class HeapOOM {


    static class OOMObject {

    }

    public static void main(String[] args) throws InterruptedException {

        List<OOMObject> list = new ArrayList<>();

        Thread.sleep(5000L);

        while (true) {
            list.add(new OOMObject());
        }
    }
}
