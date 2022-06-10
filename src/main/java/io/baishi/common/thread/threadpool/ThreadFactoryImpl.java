package io.baishi.common.thread.threadpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 线程池工厂
 * <p>
 * 定义线程前缀
 *
 * @author baishi
 * @date 2022/1/18 11:25
 */
public class ThreadFactoryImpl implements ThreadFactory {

    private final AtomicLong threadIndex = new AtomicLong(0);
    private final String threadNamePrefix;
    private final boolean daemon;

    public ThreadFactoryImpl(final String threadNamePrefix) {
        this(threadNamePrefix, false);
    }

    public ThreadFactoryImpl(final String threadNamePrefix, boolean daemon) {
        this.threadNamePrefix = threadNamePrefix;
        this.daemon = daemon;
    }

    @Override
    public Thread newThread(Runnable runnable) {

        Thread thread = new Thread(runnable);

        thread.setName(threadNamePrefix + this.threadIndex.incrementAndGet());
        thread.setDaemon(daemon);

        return thread;
    }
}
