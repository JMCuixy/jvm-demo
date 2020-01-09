package org.jvm.demo.chapter2;

/**
 * 虚拟机栈和本地方法栈溢出（HotSpot 虚拟机不区分虚拟机栈和本地方法区）
 * Vm Args：
 * -Xoss128K 设置本地方法栈（HotSpot 虚拟机上不生效）
 * -Xss128K 设置虚拟机栈大小
 *
 * @author : cuixiuyin
 * @date : 2019/11/13
 */
public class JavaVMStackSOF {

    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable {
        JavaVMStackSOF stackSOF = new JavaVMStackSOF();
        Thread.sleep(5000);
        try {
            stackSOF.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + stackSOF.stackLength);
            throw e;
        }
    }
}
