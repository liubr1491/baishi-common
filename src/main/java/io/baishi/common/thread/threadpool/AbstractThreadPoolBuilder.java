package io.baishi.common.thread.threadpool;

import java.util.concurrent.*;

/**
 * 线程池构造参数有8个，但是最核心的是3个：corePoolSize、maximumPoolSize，workQueue，
 * 它们最大程度地决定了线程池的任务分配和线程分配策略。
 * <p>
 * <p>1. 并行执行子任务，提高响应速度。</p>
 * <p>>>>>这种情况下，应该使用同步队列，没有什么任务应该被缓存下来，而是应该立即执行。</p>
 * <p>2. 并行执行大批次任务，提升吞吐量。</p>
 * <p>>>>>这种情况下，应该使用有界队列，使用队列去缓冲大批量的任务，队列容量必须声明，防止任务无限制堆积。</p>
 *
 * @author baishi
 * @date 2022/1/18 15:08
 */
public abstract class AbstractThreadPoolBuilder {

    /**
     * 默认拒绝策略
     */
    protected static final RejectedExecutionHandler DEFAULT_REJECT_HANDLER = new ThreadPoolExecutor.AbortPolicy();

    /**
     * CPU 核心数量
     */
    protected static final int CPU_COUNT = MachineUtils.getCpuCount();

    protected ThreadFactory threadFactory;

    protected RejectedExecutionHandler rejectedExecutionHandler;

    /**
     * 任务队列大小
     * <p>
     * 默认为-1，表示不限制大小，使用{@link LinkedBlockingDeque}存储
     */
    protected int queueSize = -1;

    protected int maximumPoolSize = CPU_COUNT;

    protected int keepAliveTimeMilliSeconds = 1000;

    protected boolean daemon = false;

    protected String threadNamePrefix;

    /**
     * 最大线程数
     *
     * @return
     */
    protected int getMaximumPoolSize(int coreSize) {
        return Math.max(coreSize, maximumPoolSize);
    }

    protected BlockingQueue<Runnable> getBlockingQueue() {
        return queueSize < 1 ?
                new LinkedBlockingDeque<>() :
                new ArrayBlockingQueue<>(queueSize);
    }

    protected RejectedExecutionHandler getRejectedExecutionHandler() {

        if (rejectedExecutionHandler == null) {
            rejectedExecutionHandler = DEFAULT_REJECT_HANDLER;
        }

        return rejectedExecutionHandler;
    }
}
