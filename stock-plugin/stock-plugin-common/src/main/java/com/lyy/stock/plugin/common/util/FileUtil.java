package com.lyy.stock.plugin.common.util;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @Author:
 * @createTime: 2023/03/23 17:57:28
 * @version:
 * @Description:
 */
public class FileUtil {

    private static final Logger log = LoggerFactory.getLogger(FileUtil.class);

    public static InputStream getInputStream(ApplicationContext applicationContext, String path) {
        if (StringUtils.isEmpty(path)) {
            log.warn("load file failed, path entity : {}", path);
            return null;
        }
        try {
            String filePath = applicationContext.getEnvironment().resolvePlaceholders(path);
            InputStream inputStream = applicationContext.getResource(filePath).getInputStream();
            log.info("load file success : {}", path);
            return inputStream;
        } catch (Exception e) {
            log.warn("load file failed : {}", path);
        }
        return null;
    }

    public static String getText(ApplicationContext applicationContext, String path) {
        InputStream inputStream = null;
        try {
            inputStream = getInputStream(applicationContext, path);
            if (inputStream != null) {
                try {
                    return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
                } catch (IOException e) {
                    log.warn("load file failed : {}", path);
                }
            }
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
