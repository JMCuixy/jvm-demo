package org.jvm.demo.chapter11;

/**
 * 查看和分析即时编译结果
 * <p>
 * 一般来说，虚拟机的即时编译过程对用户程序是完全透明的，虚拟机通过解释器执行代码还是编译器执行代码
 * 对执行结果没有影响，速度上会有很大差别。
 * <p>
 * 虚拟机提供了一些参数用来输出即时编译和某些优化手段的执行状况。
 *
 * @author : cuixiuyin
 * @date : 2020/1/16
 */
public class Test1 {

    public static final int NUM = 15000;

    public static int doubleValue(int i) {
        // 这个空循环用于后面演示 JIT 代码优化过程
        for (int j = 0; j < 10000; j++) ;
        return i + 2;
    }

    public static long calcSum() {
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum += doubleValue(i);
        }
        return sum;
    }

    /**
     * -XX:+PrintCompilation 虚拟机在即时编译时将被编译成本地代码的方法名称打印出来
     * -XX:+PrintInlining 虚拟机输出方法内联信息
     * -XX:+PrintAssembly 虚拟机打印编译方法的汇编代码（需要反汇编适配器的支持）
     * -XX:+UnlockDiagnosticVMOptions 打开虚拟机诊断模式
     * -XX:+PrintOptoAssembly 用于 Server VM 输出比较接近最终结果的中间代码（debug 版本虚拟机支持）
     * -XX:+PrintLIR 用于 Client VM 输出比较接近最终结果的中间代码（debug 版本虚拟机支持）
     *
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < NUM; i++) {
            calcSum();
        }
    }
}
