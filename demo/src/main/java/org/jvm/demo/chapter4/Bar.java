package org.jvm.demo.chapter4;

/**
 * -server -XX:+UnlockDiagnosticVMOptions -XX:+TraceClassLoading  -XX:+PrintAssembly -XX:+LogCompilation -XX:LogFile=live.log
 * <p>
 * HSDIS: HotSpot 虚拟机 JIT 编译代码的反汇编插件。
 * JITWATCH：查看 HSDIS 反汇编后的汇编码
 *
 * @author : cuixiuyin
 * @date : 2020/3/15
 */
public class Bar {
    int a = 1;
    static int b = 2;

    public int sum(int c) {
        return a + b + c;
    }

    public static void main(String[] args) {
        new Bar().sum(3);
    }
}

