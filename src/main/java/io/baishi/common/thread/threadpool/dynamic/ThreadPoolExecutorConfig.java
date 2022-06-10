package io.baishi.common.thread.threadpool.dynamic;

import io.baishi.common.PropertiesUtils;
import io.baishi.common.thread.threadpool.MachineUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * 使用配置，实现线程池的动态配置
 *
 * @author baishi
 * @date 2022/1/19 09:29
 */
public class ThreadPoolExecutorConfig {

    /**
     * 核心线程数
     *
     * @return
     */
    public static int getCorePoolSize() {
        String value = PropertiesUtils.getValue("core.pool.size");
        return NumberUtils.toInt(value, MachineUtils.getCpuCount());
    }

    /**
     * 最大线程数
     *
     * @return
     */
    public static int getMaximumPoolSize() {
        String value = PropertiesUtils.getValue("maximum.pool.size");
        return NumberUtils.toInt(value, MachineUtils.getCpuCount());
    }
}
