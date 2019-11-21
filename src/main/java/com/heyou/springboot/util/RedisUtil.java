package com.heyou.springboot.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author heyou
 * @date 2019-11-21 16:38
 */
@Component
public class RedisUtil {
    @Resource
    RedisTemplate redisTemplate;

    public static RedisTemplate redisTemplateStatic;

    @PostConstruct
    public void init() {
        redisTemplateStatic = redisTemplate;
    }
}
