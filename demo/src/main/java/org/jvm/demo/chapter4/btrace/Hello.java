package org.jvm.demo.chapter4.btrace;


import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;

/**
 * @author : cuixiuyin
 * @date : 2020/3/26
 */

@BTrace(unsafe = true)
public class Hello {

    @OnMethod(clazz = "org.jvm.demo.chapter4.btrace.BtraceCase",
            method = "add",
            location = @Location(Kind.RETURN)
    )
    public static void run(@Self Object self, int a, int b,
                           @Return int result,
                           @Duration long time
    ) {
        BTraceUtils.print(self);
        BTraceUtils.print("print param, a = " + a + ",b=" + b);
        BTraceUtils.print("print return, result = " + result);
        BTraceUtils.print("print cost, time = " + time);
    }
}
