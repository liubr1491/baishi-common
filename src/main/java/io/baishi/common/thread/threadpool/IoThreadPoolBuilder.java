package io.baishi.common.thread.threadpool;

import io.baishi.common.thread.threadpool.dynamic.DynamicThreadPoolExecutor;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * 处理IO密集的线程池
 * <p>
 * 最佳线程数 = CPU核数 * [ 1 +（I/O 耗时 / CPU 耗时）]
 *
 * @author baishi
 * @date 2022/1/17 14:50
 */
public class IoThreadPoolBuilder extends AbstractThreadPoolBuilder {

    /**
     * 计算核心线程数量
     *
     * @param ioTime
     * @param cpuTime
     * @return
     */
    public int getCorePoolSize(int ioTime, int cpuTime) {
        return CPU_COUNT + (1 + (ioTime / cpuTime));
    }

    public IoThreadPoolBuilder setThreadFactory(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
        return this;
    }

    public IoThreadPoolBuilder setRejectedExecutionHandler(RejectedExecutionHandler rejectedExecutionHandler) {
        this.rejectedExecutionHandler = rejectedExecutionHandler;
        return this;
    }

    public IoThreadPoolBuilder setQueueSize(int queueSize) {
        this.queueSize = queueSize;
        return this;
    }

    public IoThreadPoolBuilder setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
        return this;
    }

    public IoThreadPoolBuilder setKeepAliveTimeMilliSeconds(int keepAliveTimeMilliSeconds) {
        this.keepAliveTimeMilliSeconds = keepAliveTimeMilliSeconds;
        return this;
    }

    public IoThreadPoolBuilder setDaemon(boolean daemon) {
        this.daemon = daemon;
        return this;
    }

    public IoThreadPoolBuilder setThreadNamePrefix(String threadNamePrefix) {
        this.threadNamePrefix = threadNamePrefix;
        return this;
    }

    public DynamicThreadPoolExecutor builder(int ioTime, int cpuTime) {

        threadFactory = ThreadPoolUtils.createThreadFactory(this.threadNamePrefix, this.daemon);

        return new DynamicThreadPoolExecutor(
                getCorePoolSize(ioTime, cpuTime),
                getMaximumPoolSize(getCorePoolSize(ioTime, cpuTime)),
                keepAliveTimeMilliSeconds,
                TimeUnit.MILLISECONDS,
                getBlockingQueue(),
                threadFactory,
                getRejectedExecutionHandler());
    }
}
