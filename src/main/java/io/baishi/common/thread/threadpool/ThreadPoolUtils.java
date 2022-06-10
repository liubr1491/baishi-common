package io.baishi.common.thread.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 线程池工具类
 *
 * @author baishi
 * @date 2022/1/18 11:32
 */
public class ThreadPoolUtils {

    public static ThreadFactory createThreadFactory(String threadNamePrefix, boolean daemon) {
        if (threadNamePrefix != null) {
            return new ThreadFactoryImpl(threadNamePrefix, daemon);
        }
        return Executors.defaultThreadFactory();
    }

}
