package com.heyou.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @author heyou(heyou_0423 @ 163.com)
 * @date 2021/3/16 下午10:44
 */
@RestController
@RequestMapping(value = "/redis/cluster")
public class RedisClusterController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping(value = "/set")
    public void setRedisValue(@RequestParam("value")String value){
        String key = "heyou";
        stringRedisTemplate.opsForValue().set(key,value);
        System.out.println(stringRedisTemplate.opsForValue().get(key));
    }
}
