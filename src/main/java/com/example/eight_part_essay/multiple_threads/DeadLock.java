package com.example.eight_part_essay.multiple_threads;

import lombok.AllArgsConstructor;


import java.util.concurrent.TimeUnit;

/**
 * @author: zy
 * @date: 2022/3/2 20:06
 * @since JDK 1.8
 * 死锁条件： 资源互斥   不可剥夺   请求保持   循环等待
 */

public class DeadLock {
@AllArgsConstructor
protected static class Resource implements Runnable {
   private String a;
   private String b;
    //    使用sync锁互相持有调用对方
    @Override
    public void run() {
        synchronized (a) {
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(2L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (b) {
                System.out.println(Thread.currentThread().getName());
            }
         }
        }
    }

    public static void main(String[] args) {
        String a = "a";
        String b = "b";

        new Thread(new Resource(a, b), "ThreadA").start();
        new Thread(new Resource(b, a), "ThreadB").start();
    }
}
