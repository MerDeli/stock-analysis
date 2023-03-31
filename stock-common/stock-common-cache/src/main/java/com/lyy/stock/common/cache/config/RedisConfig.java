package com.lyy.stock.common.cache.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import com.lyy.stock.common.cache.utils.RedisUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Author:
 * @createTime: 2023/03/31 15:17:36
 * @version:
 * @Description:
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisSerializer<String> redisKeySerializer() {
        return new StringRedisSerializer();
    }

    /**
     *
     * @description 使用FastJson
     * @param
     * @author huangfubin
     * @date 2021/8/5
     * @return org.springframework.data.redis.serializer.RedisSerializer<java.lang.Object>
     */
    @Bean
    public RedisSerializer<Object> redisValueSerializer() {
        return new GenericFastJsonRedisSerializer();
    }

    /**
     *
     * @description RedisTemplate配置
     * @param factory
     * @param redisKeySerializer
     * @param redisValueSerializer
     * @author huangfubin
     * @date 2021/8/5
     * @return org.springframework.data.redis.core.RedisTemplate<java.lang.String,java.lang.Object>
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory, RedisSerializer<String> redisKeySerializer, RedisSerializer<Object> redisValueSerializer) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        //设置Key的序列化采用StringRedisSerializer
        redisTemplate.setKeySerializer(redisKeySerializer);
        redisTemplate.setHashKeySerializer(redisKeySerializer);
        //设置值的序列化
        redisTemplate.setValueSerializer(redisValueSerializer);
        redisTemplate.setHashValueSerializer(redisValueSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public RedisUtil redisUtil(){
        return new RedisUtil();
    }
}
