package org.jvm.demo.chapter4;

/**
 * -server -Xcomp -XX:+UnlockDiagnosticVMOptions  -XX:+PrintAssembly -XX:+LogCompilation -XX:LogFile=live.log
 * <p>
 * -server：以 server 模式运行
 * -Xcomp：以编译模式执行代码
 * -XX:+UnlockDiagnosticVMOptions：Prodect 版本的 hotspot 需要添加
 *
 * </p>
 * HSDIS: HotSpot 虚拟机 JIT 编译代码的反汇编插件。它包含在 HotSpot 虚拟机的源码之中，但没有提供编译好的程序。读者可以下载编译好的
 * 插件，直接放到 JDK_HOME/jre/bin/client 和 JDK_HOME/jre/bin/server 目录下，它的作用是让 -XX:+PrintAssembly 指令调用它来把动态
 * 生成的本地代码还原为汇编代码输出，同时还生成了大量非常有价值的注释，这样我们就能通过输出的代码来分析问题。
 * <p>
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

