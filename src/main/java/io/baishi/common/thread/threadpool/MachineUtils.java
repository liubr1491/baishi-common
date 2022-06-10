package io.baishi.common.thread.threadpool;

import java.io.File;

/**
 * JVM运行的所在物理机、虚拟机、Docker的硬件配置情况
 *
 * @author baishi
 * @date 2022/1/18 14:40
 */
public class MachineUtils {

    private MachineUtils() {
    }

    /**
     * CPU 核心数量
     *
     * @return
     */
    public static int getCpuCount() {
        return Runtime.getRuntime().availableProcessors();
    }

    /**
     * 获取所在机器剩余的磁盘空间
     * <p>
     * 根路径下的剩余磁盘空间
     *
     * @return
     */
    public static long getFreeDiskSpace() {
        File diskPartition = new File("/");
        return diskPartition.getUsableSpace();
    }
}
