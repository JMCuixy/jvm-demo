package org.demo.jvm.chapter3;

/**
 * 对象分配策略
 *
 * @author : cuixiuyin
 * @date : 2019/12/19
 */
public class Allocation {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        //testAllocation();
        //testPretenureSizeThreshold();
        testMaxTenuringThreshold();
    }


    /**
     * VM 参数：-XX:+UseSerialGC -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
     * <p>
     * todo: -XX:MaxTenuringThreshold=15 的时候也直接进入老年代了
     * 测试长期存活的对象将进入老年代
     */
    public static void testMaxTenuringThreshold() {
        byte[] allo1, allo2, allo3;
        allo1 = new byte[_1MB / 4];
        allo2 = new byte[_1MB * 4];
        allo3 = new byte[_1MB * 4];
        allo3 = null;
        allo3 = new byte[_1MB * 4];
    }


    /**
     * VM 参数：-XX:+UseSerialGC -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
     * <p>
     * 测试大对象直接进入老年代
     */
    public static void testPretenureSizeThreshold() {
        byte[] allos;
        allos = new byte[4 * _1MB];
    }

    /**
     * VM 参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * <p>
     * 验证当新生代内存不足以分配时，将发生一次 Minor GC（年轻代GC）
     */
    public static void testAllocation() {
        byte[] allo1, allo2, allo3, allo4;
        allo1 = new byte[2 * _1MB];
        allo2 = new byte[2 * _1MB];
        allo3 = new byte[2 * _1MB];
        // 出现一次 Minor GC
        allo4 = new byte[4 * _1MB];
    }


}
