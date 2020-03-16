package org.jvm.demo.chapter4;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * JVM 中所有的线程堆栈信息
 *
 * @author : cuixiuyin
 * @date : 2020/3/15
 */
public class AllStackTracesTest {

    public static void main(String[] args) {
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        Set<Map.Entry<Thread, StackTraceElement[]>> entrySet = allStackTraces.entrySet();
        Iterator<Map.Entry<Thread, StackTraceElement[]>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Thread, StackTraceElement[]> entry = iterator.next();
            Thread thread = entry.getKey();
            StackTraceElement[] traceElements = entry.getValue();
            if (thread.equals(Thread.currentThread())) {
                continue;
            }
            System.out.println("线程：" + thread.getName());
            for (StackTraceElement element : traceElements) {
                System.out.println(element);
            }
        }
    }

}
