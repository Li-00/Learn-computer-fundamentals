package com.example.eight_part_essay.multiple_threads;

import sun.nio.ch.ThreadPool;

import java.util.concurrent.*;

/**
 * @author: zy
 * @date: 2022/3/5 19:16
 * @since JDK 1.8
 * //int corePoolSize核心线程数   int maximumPoolSize=核心线程数+非核心线程数   workQueueSize工作队列 SynchronousQueue,LinkedBlockingDeque,ArrayBlockingQueue
 * //long keepAliveTime非核心线程闲置超时时长    TimeUnit unit枚举类型，超时单位
 * 该线程池中的任务队列：维护着等待执行的Runnable对象
 *
 * 当所有的核心线程都在干活时，新添加的任务会被添加到这个队列中等待处理，如果队列满了，则新建非核心线程执行任务
 *
 *
 */

public class ThreadPoolTest {
    ExecutorService s  = Executors.newCachedThreadPool();
/**创建核心数为3  最大线程数为5  超时时间为5s   阻塞队列为加指定FIFO的队列
 *  Executors为Executor、ExecutorService、ScheduledExecutorService、ThreadFactory 和 Callable 类提供了一些工具方法。Executors 可以用于方便的创建线程池。
 */
    ThreadPoolExecutor sync = new ThreadPoolExecutor(3, 5, 5L, TimeUnit.SECONDS, new SynchronousQueue<>(true));


}
