package org.demo.jvm.chapter3.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Hashtable;

/**
 * 通过软可及对象重获方法实现Java对象的高速缓存
 *
 * @author : cuixiuyin
 * @date : 2019/12/15
 */
public class EmployeeCache {
    /**
     * 一个 Cache 实例
     */
    private static volatile EmployeeCache cache;
    /**
     * 用于 Chche 内容的存储
     */
    private Hashtable<String, EmployeeRef> employeeRefs;
    /**
     * 垃圾 Reference 的队列（当软引用对象被回收的时候，会将 SoftReference 保存到该队列）
     */
    private ReferenceQueue<Employee> queue;

    // 构建一个缓存器实例
    private EmployeeCache() {
        employeeRefs = new Hashtable<>();
        queue = new ReferenceQueue<>();
    }

    // 取得缓存器实例
    public static EmployeeCache getInstance() {
        if (cache == null) {
            synchronized (EmployeeCache.class) {
                if (cache == null) {
                    cache = new EmployeeCache();
                }
            }
        }
        return cache;
    }

    // 以软引用的方式对一个Employee对象的实例进行引用并保存该引用
    private void cacheEmployee(Employee em) {
        // 清除垃圾引用
        cleanCache();
        EmployeeRef ref = new EmployeeRef(em, queue);
        employeeRefs.put(em.getId(), ref);
    }

    // 依据所指定的ID号，重新获取相应Employee对象的实例
    public Employee getEmployee(String id) {
        Employee em = null;
        // 缓存中是否有该Employee实例的软引用，如果有，从软引用中取得。
        if (employeeRefs.containsKey(id)) {
            EmployeeRef ref = employeeRefs.get(id);
            em = ref.get();
        }
        // 如果没有软引用，或者从软引用中得到的实例是null，重新构建一个实例，
        // 并保存对这个新建实例的软引用
        if (em == null) {
            em = new Employee(id);
            System.out.println("Retrieve From EmployeeInfoCenter. ID=" + id);
            cacheEmployee(em);
        }
        return em;
    }

    // 清除那些所软引用的Employee对象已经被回收的EmployeeRef对象
    private void cleanCache() {
        EmployeeRef ref;
        while ((ref = (EmployeeRef) queue.poll()) != null && ref.get() == null) {
            employeeRefs.remove(ref._key);
        }
    }


    // 继承SoftReference，使得每一个实例都具有可识别的标识。
    // 并且该标识与其在 HashMap 内的key相同。
    // 如果 SoftReference 对象没有被回收，则通过 SoftReference.get() 可以返回强引用对象，否则返回 null。
    private class EmployeeRef extends SoftReference<Employee> {

        private String _key;

        public EmployeeRef(Employee em, ReferenceQueue<Employee> q) {
            super(em, q);
            _key = em.getId();
        }
    }

    public static void main(String[] args) {
        EmployeeCache employeeCache = getInstance();
        Employee employee1 = employeeCache.getEmployee("11111");
        System.out.println("employee1：" + employee1.getName());
        employee1 = null;
        Employee employee2 = employeeCache.getEmployee("11111");
        System.out.println("employee2：" + employee2.getName());
        employee2 = null;
        System.gc();
        Employee employee3 = employeeCache.getEmployee("11111");
        System.out.println("employee3：" + employee3.getName());
    }
}
