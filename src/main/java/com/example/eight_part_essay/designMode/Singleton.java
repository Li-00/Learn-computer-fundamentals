package com.example.eight_part_essay.designMode;

/**
 * @author: zy
 * @date: 2022/3/7 21:59
 * @since JDK 1.8
 * 单例模式
 */

interface Singleton {
//    饿汉方法
    Hungry getSingleton();
//    懒汉方法
    Lazy getSingletons();
}
