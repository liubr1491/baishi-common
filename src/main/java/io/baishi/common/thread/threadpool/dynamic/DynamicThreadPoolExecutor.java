package io.baishi.common.thread.threadpool.dynamic;

import java.util.concurrent.*;

/**
 * 基于Qconfig动态配置线程池
 * <p>
 * ThreadPoolExecutor提供了setCorePoolSize()、setMaximumPoolSize()、setRejectedExecutionHandle()等方法，可对线程池进行动态参数配置。
 * <p>
 * 阻塞队列本身没有对外提供修改队列大小的方法，那我们需要继承AbstractQueue然后增加一个setCapacity()方法即可实现改功能
 *
 * @author baishi
 * @date 2022/1/18 20:38
 */
public class DynamicThreadPoolExecutor extends ThreadPoolExecutor {

    public DynamicThreadPoolExecutor(int corePoolSize,
                                     int maximumPoolSize,
                                     long keepAliveTime,
                                     TimeUnit unit,
                                     BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public DynamicThreadPoolExecutor(int corePoolSize,
                                     int maximumPoolSize,
                                     long keepAliveTime,
                                     TimeUnit unit,
                                     BlockingQueue<Runnable> workQueue,
                                     ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public DynamicThreadPoolExecutor(int corePoolSize,
                                     int maximumPoolSize,
                                     long keepAliveTime,
                                     TimeUnit unit,
                                     BlockingQueue<Runnable> workQueue,
                                     RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public DynamicThreadPoolExecutor(int corePoolSize,
                                     int maximumPoolSize,
                                     long keepAliveTime,
                                     TimeUnit unit,
                                     BlockingQueue<Runnable> workQueue,
                                     ThreadFactory threadFactory,
                                     RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    /**
     * 更新线程池配置
     */
    public void update() {
        this.setCorePoolSize(ThreadPoolExecutorConfig.getCorePoolSize());
        this.setMaximumPoolSize(ThreadPoolExecutorConfig.getMaximumPoolSize());
    }
}
