package org.demo.jvm.chapter10;

import java.util.HashMap;
import java.util.List;

/**
 * @author : cuixiuyin
 * @date : 2019/12/24
 */
public class Generic {

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("hello", "hello");
        map.put("world", "world");
        System.out.println(map.get("hello"));
        System.out.println(map.get("world"));
    }

    public static <T1, T2> T1 print(List<T1> list, List<T2> list2) {
        return list.get(0);
    }

}
