package org.jvm.demo.chapter4.btrace;

import org.openjdk.btrace.core.BTraceUtils;
import org.openjdk.btrace.core.annotations.*;

/**
 * @author : cuixiuyin
 * @date : 2020/3/26
 */

@BTrace//表示这是一个BTrace跟踪脚本
public class Hello {

    @OnMethod(clazz = "org.jvm.demo.chapter4.btrace.BtraceCase", // 全类名
            method = "add", // 方法名
            location = @Location(Kind.RETURN) // 表示跟踪某个类的某个方法，位置为方法返回处
    )
    public static void run(@Self Object self, int a, int b, // 入参，按顺序定义
                           @Return int result, // 出参
                           @Duration long time // 方法耗时
    ) {
        BTraceUtils.print(self);
        BTraceUtils.print("打印入参, a = " + a + ",b=" + b);
        BTraceUtils.print("打印出参, result = " + result);
        BTraceUtils.print("打印耗时，time = " + time);
    }
}
