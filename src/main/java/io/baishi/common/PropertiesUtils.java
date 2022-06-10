package io.baishi.common;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Properties;

/**
 * @author baishi
 * @date 2022/6/10 09:34
 */
public class PropertiesUtils {

    /**
     * 根据clazz的物理路径，获取propertyFileName名称的属性文件的Properties对象
     *
     * @param clazz
     * @param propertyFileName
     * @return
     */
    public static Properties getPropertyFile(Class clazz, String propertyFileName) throws IOException {
        InputStreamReader reader = new InputStreamReader(
                Objects.requireNonNull(
                        clazz.getClassLoader().getResourceAsStream(propertyFileName)));

        Properties p = new Properties();
        p.load(reader);

        return p;
    }

    /**
     * 默认路径
     *
     * @param key
     * @return
     */
    public static String getValue(String key) {
        return getValue("classpath:META-INF/config.properties", key);
    }

    /**
     * 指定路径
     *
     * @param fileName
     * @param key
     * @return
     */
    public static String getValue(String fileName, String key) {
        try {
            Properties prop = getPropertyFile(PropertiesUtils.class, fileName);
            return prop.getProperty(key);
        } catch (IOException e) {
            return null;
        }
    }
}