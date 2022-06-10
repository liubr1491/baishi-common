package io.baishi.common.thread.threadpool;

import io.baishi.common.thread.threadpool.dynamic.DynamicThreadPoolExecutor;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * 处理CPU密集的线程池
 * <p>
 * 最佳线程数 = CPU核数 + 1
 *
 * @author baishi
 * @date 2022/1/17 14:48
 */
public class CpuThreadPoolBuilder extends AbstractThreadPoolBuilder {

    /**
     * 计算核心线程数量
     *
     * @return
     */
    public int getCorePoolSize() {
        return CPU_COUNT + 1;
    }

    public CpuThreadPoolBuilder setThreadFactory(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
        return this;
    }

    public CpuThreadPoolBuilder setRejectedExecutionHandler(RejectedExecutionHandler rejectedExecutionHandler) {
        this.rejectedExecutionHandler = rejectedExecutionHandler;
        return this;
    }

    public CpuThreadPoolBuilder setQueueSize(int queueSize) {
        this.queueSize = queueSize;
        return this;
    }

    public CpuThreadPoolBuilder setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
        return this;
    }

    public CpuThreadPoolBuilder setKeepAliveTimeMilliSeconds(int keepAliveTimeMilliSeconds) {
        this.keepAliveTimeMilliSeconds = keepAliveTimeMilliSeconds;
        return this;
    }

    public CpuThreadPoolBuilder setDaemon(boolean daemon) {
        this.daemon = daemon;
        return this;
    }

    public CpuThreadPoolBuilder setThreadNamePrefix(String threadNamePrefix) {
        this.threadNamePrefix = threadNamePrefix;
        return this;
    }

    public DynamicThreadPoolExecutor builder() {

        threadFactory = ThreadPoolUtils.createThreadFactory(this.threadNamePrefix, this.daemon);

        return new DynamicThreadPoolExecutor(
                getCorePoolSize(),
                getMaximumPoolSize(getCorePoolSize()),
                keepAliveTimeMilliSeconds,
                TimeUnit.MILLISECONDS,
                getBlockingQueue(),
                threadFactory,
                getRejectedExecutionHandler());
    }
}
