package com.lyy.stock.plugin.common.util;

import com.alibaba.spring.util.PropertySourcesUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author:
 * @createTime: 2023/03/22 16:28:42
 * @version:
 * @Description:
 */
public class PropertiesUtil {
    public static final Pattern PATTERN = Pattern.compile("-(\\w)");

    public static void enrichProperties(Properties properties, Environment environment, String prefix, boolean replaceExistedKey, boolean ignoreEmptyValue) {
        enrichProperties(properties, environment, PATTERN, prefix, replaceExistedKey, ignoreEmptyValue);
    }

    public static void enrichProperties(Properties properties, Environment environment, Pattern pattern, String prefix, boolean replaceExistedKey, boolean ignoreEmptyValue) {
        Map<String, Object> map = PropertySourcesUtils.getSubProperties((ConfigurableEnvironment) environment, prefix);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = resolveKey(pattern, entry.getKey());
            String value = String.valueOf(entry.getValue());

            addProperty(properties, key, value, replaceExistedKey, ignoreEmptyValue);
        }
    }

    public static void addProperty(Properties properties, String key, String value, boolean replaceExistedKey, boolean ignoreEmptyValue) {
        if (properties.containsKey(key)) {
            if (replaceExistedKey) {
                addProperty(properties, key, value, ignoreEmptyValue);
            }
        } else {
            addProperty(properties, key, value, ignoreEmptyValue);
        }
    }

    public static void addProperty(Properties properties, String key, String value, boolean ignoreEmptyValue) {
        if (StringUtils.isBlank(value)) {
            if (!ignoreEmptyValue) {
                properties.put(key, value);
            }
        } else {
            properties.put(key, value);
        }
    }

    public static String resolveKey(String key) {
        return resolveKey(PATTERN, key);
    }

    public static String resolveKey(Pattern pattern, String key) {
        Matcher matcher = pattern.matcher(key);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(stringBuffer, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(stringBuffer);

        return stringBuffer.toString();
    }
}
