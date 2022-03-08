package com.example.eight_part_essay.designMode;

/**
 * @author: zy
 * @date: 2022/3/7 22:30
 * @since JDK 1.8
 * 懒汉
 * 优点： 延迟实例化，节约了资源，并且是线程安全的。
 *
 * 缺点： 虽然解决了线程安全问题，但是性能降低了。因为，即使实例已经实例化了，既后续不会再出现线程安全问题了，
 * 但是锁还在，每次还是只能拿到锁的线程进入该方法，会使线程阻塞，等待时间过长。
 */

public class Lazy {
    private static volatile Lazy lazy = null;
    private Lazy(){
    }

//    双重检查锁  保障线程安全
    public static Lazy getSingletons() {
        if (lazy == null) {
            synchronized (Lazy.class) {
                if (lazy == null) {
                    lazy = new Lazy();
                }
            }
        }
        return lazy;
    }



}
