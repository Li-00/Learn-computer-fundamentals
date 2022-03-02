package com.example.eight_part_essay.multiple_threads;

import lombok.AllArgsConstructor;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: zy
 * @date: 2022/3/2 20:24
 * @since JDK 1.8
 * 使用手动锁实现死锁   lock持有等待尝试获取另一个线程
 */

public class DeadLock2 {
    public static void main(String[] args) {
        Lock lockA = new ReentrantLock(true);
        Lock lockB = new ReentrantLock(true);

        new Thread(new Resource2(lockA, lockB), "ThreadA").start();
        new Thread(new Resource2(lockB, lockA), "ThreadB").start();
    }

    @AllArgsConstructor
    protected static class Resource2 implements Runnable {
        Lock lockA;
        Lock lockB;
        @Override
        public void run() {
            lockA.lock();
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(2000);
                lockB.lock();

                try {
                    System.out.println(Thread.currentThread().getName());
                } finally {
                    lockB.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lockA.unlock();
            }
        }
    }
}
