package com.example.eight_part_essay.multiple_threads;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

//多个线程运行同一静态方法，静态方法只用了方法内的变量，会造成线程不安全吗
public class RunStaticMethod {
    static int num = 0;

//    测试全局变量
    private static void add() {
       num++;
       System.out.println(Thread.currentThread().getName() + "  num: "  + num);
    }

    //   有参局部变量
    private void addNum(int num) {
        num++;
        System.out.println(Thread.currentThread().getName() + "  num: "  + num);
    }

     //   无参局部变量
    private static void adds() {
        int nums = 0;
        nums++;
        System.out.println(Thread.currentThread().getName() + "  num: "  + nums);
    }


    public static class RunThreadAdd implements Callable {
        RunStaticMethod runStaticMethod = new RunStaticMethod();
        int a;
        @Override
        public String call() throws Exception {
            Thread.sleep(2000);
            for (int i = 0; i < 5; i++) {
                adds();
                System.out.println("--------");
            }

            return "success";
        }
    }

    public static void main(String[] args) {

        RunThreadAdd runThreadAdd1 = new RunThreadAdd();
        RunThreadAdd runThreadAdd2 = new RunThreadAdd();

        FutureTask<String> futureTask1 = new FutureTask<>(runThreadAdd1);
        FutureTask<String> futureTask2 = new FutureTask<>(runThreadAdd2);
        new Thread(futureTask1, "Thread-1").start();

        new Thread(futureTask2, "Thread-2").start();

    }


}


/*测试结果
 *调用静态方法涉及到方法外静态变量不是线程安全  add()执行结果 不定
 *
 * 调用静态方法使用内部变量线程安全  adds  addNum 执行结果固定
 *
 * 因为，在多线程中使用同一个静态方法时，每个线程使用各自的实例字段(instance field)的副本，而共享一个静态字段(static field)。
 * 所以说，如果该静态方法不去操作一个静态成员，只在方法内部使用实例字段(instance field)，不会引起安全性问题。
 * 但是，如果该静态方法操作了一个静态字段，则需要静态方法中采用互斥访问的方式进行安全处理。
 */