package com.heyou.springboot.service.redis;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * @author heyou
 * @date 2019-11-26 19:51
 */
public class RedisHashService {
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    public void put(String mapName, String key, Object value) {
        redisTemplate.boundHashOps(mapName).put(key, value);
    }

    public Object get(String mapName, String key) {
        return redisTemplate.boundHashOps(mapName).get(key);
    }

    public void delete(String mapName, String key) {
        redisTemplate.boundHashOps(mapName).delete(key);
    }

    public Boolean hasKey(String mapName, String key) {
        return redisTemplate.boundHashOps(mapName).hasKey(key);
    }

   /* public String set(final String key, final String value, final String nxxx, final String expx, final long time) {
        return (String) redisTemplate.execute((RedisCallback<Object>) connection -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            Object object = connection.execute("set",
                    serializer.serialize(key), serializer.serialize(value),
                    serializer.serialize(nxxx), serializer.serialize(expx));
                    SafeEncoder.encode(String.valueOf(time)));
            return serializer.deserialize((byte[]) object);
        });
    }*/

    /*public Long eval(final String script, final List<String> keys, final List<String> args) {
        RedisCallback<Long> callback = connection -> {
            Object nativeConnection = connection.getNativeConnection();
            // 集群模式和单机模式虽然执行脚本的方法一样，但是没有共同的接口，所以只能分开执行
            // 集群模式
            if (nativeConnection instanceof JedisCluster) {
                return (Long) ((JedisCluster) nativeConnection).eval(script, keys, args);
            } else if (nativeConnection instanceof Jedis) {// 单机模式
                return (Long) ((Jedis) nativeConnection).eval(script, keys, args);
            }
            return 0L;
        };
        return redisTemplate.execute(callback);
    }*/

    public RedisTemplate<String, String> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
