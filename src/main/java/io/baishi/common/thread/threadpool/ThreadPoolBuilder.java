package io.baishi.common.thread.threadpool;

/**
 * 不同类型线程池构造器
 *
 * @author baishi
 * @date 2022/1/18 14:56
 */
public class ThreadPoolBuilder {

    private ThreadPoolBuilder() {
    }

    /**
     * 获取CPU密集型的线程池
     *
     * @return
     */
    public static CpuThreadPoolBuilder getCpuThreadPoolBuilder() {
        return new CpuThreadPoolBuilder();
    }

    /**
     * 获取IO密集型的线程池
     *
     * @return
     */
    public static IoThreadPoolBuilder getIoThreadPoolBuilder() {
        return new IoThreadPoolBuilder();
    }

}
