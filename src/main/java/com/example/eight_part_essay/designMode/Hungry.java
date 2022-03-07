package com.example.eight_part_essay.designMode;

/**
 * @author: zy
 * @date: 2022/3/7 22:15
 * @since JDK 1.8
 * 饿汉
 * 优点： 提前实例化好了一个实例，避免了线程不安全问题的出现。
 * 缺点： 直接实例化好了实例，不再延迟实例化；若系统没有使用这个实例，或者系统运行很久之后才需要使用这个实例，
 * 都会操作系统的资源浪费。
 */

class Hungry implements Singleton{
    private static Hungry hungry = new Hungry();
    private Hungry(){
    }
    @Override
    public  Hungry getSingleton() {
        return hungry;
    }




    @Override
    public Lazy getSingletons() {
        return null;
    }
}
