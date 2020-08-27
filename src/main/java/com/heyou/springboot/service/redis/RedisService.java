package com.heyou.springboot.service.redis;


import com.heyou.springboot.util.JsonUtil;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author heyou
 */
@Component
public class RedisService {
    /**
     * 30天有多少秒
     */
    private static final long DEFAULT_SECONDS = 30 * 24 * 60 * 60;
    @Resource
    private RedisTemplate<String, String> redisTemplate;


    /**
     * 将 key，value 存放到redis数据库中，默认设置过期时间为一周
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, JsonUtil.objectToJson(value), DEFAULT_SECONDS, TimeUnit.SECONDS);
    }

    /**
     * 将 key，value 存放到redis数据库中，设置过期时间单位是秒
     */
    public void set(String key, Object value, long expireTime) {
        redisTemplate.opsForValue().set(key, JsonUtil.objectToJson(value), expireTime, TimeUnit.SECONDS);
    }

    /**
     * 将 key，value 存放到redis数据库中，设置过期时间单位是秒
     */
    public void setStr(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 判断 key 是否在 redis 数据库中
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 获取与 key 对应的对象
     */
    public <T> T get(String key, Class<T> clazz) {
        String s = get(key);
        if (s == null) {
            return null;
        }
        return JsonUtil.jsonToObject(s, clazz);
    }

    /**
     * 获取 key 对应的字符串
     */
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 删除 key 对应的 value
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 存入名字为mapKey的map
     */
    public void mSet(String mapKey, Map<String, String> map) {
        redisTemplate.opsForHash().putAll(mapKey, map);
    }

    /**
     * 获取名字为mapKey的map
     */
    public List<Object> mGet(String mapKey) {
        return redisTemplate.opsForHash().values(mapKey);
    }

    /**
     * 获取名字为mapKey的map
     */
    public Set<Object> mGetKey(String mapKey) {
        return redisTemplate.opsForHash().keys(mapKey);
    }

    /**
     * 存入为mapKey的map中key的值
     */
    public void mSetChild(String mapKey, String key, Object value) {
        redisTemplate.opsForHash().put(mapKey, key, value);
    }

    /**
     * 获取为mapKey的map中key的值
     */
    public Object mGetChild(String mapKey, String key) {
        return redisTemplate.opsForHash().get(mapKey, key);
    }

    /**
     * 删除 key 对应的 value
     */
    public Long mDeleteChild(String mapKey, String key) {
        return redisTemplate.opsForHash().delete(mapKey, key);
    }

    /**
     * 删除 key 对应的 value
     */
    public Long mGetSize(String mapKey) {
        return redisTemplate.opsForHash().size(mapKey);
    }



}
