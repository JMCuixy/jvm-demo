package org.jvm.demo.chapter4;

import java.util.ArrayList;
import java.util.List;

/**
 * 以 64KB/50 毫秒的速度往 Java 堆中填充数据
 *
 * @author : cuixiuyin
 * @date : 2020/3/16
 */
public class OOMObject {

    public byte[] placeholder = new byte[64 * 1024];


    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> arrayList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            // 稍作延迟，令监视曲线的变化更加明显
            Thread.sleep(50);
            arrayList.add(new OOMObject());
        }
        System.gc();
    }

    public static void main(String[] args) throws InterruptedException {
        fillHeap(1000);
    }
}
