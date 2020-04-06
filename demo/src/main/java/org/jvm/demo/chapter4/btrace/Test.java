package org.jvm.demo.chapter4.btrace;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;

/**
 * @author : cuixiuyin
 * @date : 2020/4/3
 */
public class Test {

    public static void main(String[] args) {
        try {
            VirtualMachine vm = VirtualMachine.attach("17776");
            vm.loadAgent("D:\\btace\\build\\btrace-agent.jar", "port=2021,statsd=,debug=true,unsafe=true,bootClassPath=.,systemClassPath=D:\\JAVA\\jdk1.8.0_202\\lib\\tools.jar,probeDescPath=.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AttachNotSupportedException e) {
            e.printStackTrace();
        } catch (AgentInitializationException e) {
            e.printStackTrace();
        } catch (AgentLoadException e) {
            e.printStackTrace();
        }
        System.out.println(123);
    }
}
