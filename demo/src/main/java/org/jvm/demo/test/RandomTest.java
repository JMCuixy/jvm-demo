package org.jvm.demo.test;

import java.util.Random;

/**
 * @author : cuixiuyin
 * @date : 2020/1/8
 */
public class RandomTest {

    /**
     * 想要获取一个范围内的随机数（例如 26，随机数可能是 0-25），首先需要一个种子（seed，其实就是一个数值）。种子和范围是相关联的，一个种子对应一个范围内的一个固定的随机数。
     * <p>
     * 47 这个种子在 26 这个范围内，所对应的随机数为24，所以每次随机得出的结果都为 24.
     * <p>
     * 如果不填种子，则会默认取当前时间的毫秒数作为种子来生成随机数。因为毫秒数不同，生成的随机数自然也不相同。
     * <p>
     * （注意：47在26这个范围内对应的是24，这个是死的，固定的，无论你执行多少次，它还是24）
     * <p>
     * 至于为什么种子47会对应24，这个涉及到java封装的算法，有兴趣可以深入了解。
     */
    public static void main(String[] args) {
        Random random = new Random(47);
        System.out.println(random.nextInt(26));
    }
}
