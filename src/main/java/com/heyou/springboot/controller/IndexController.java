package com.heyou.springboot.controller;

import com.heyou.springboot.sevice.redis.RedisService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@Api("redis测试")
@RestController
@RequestMapping("/index/*")
public class IndexController {
    @Resource
    private RedisService redisService;

    @GetMapping("/setString")
    public String setString(String key, String value) {
        System.out.println("请求中..");
        redisService.set(key, value, 60l);
        return "success";
    }

    @GetMapping("/getString")
    public String getString(String key) {
        System.out.println(redisService.getString(key));
        return redisService.getString(key);
    }

    @GetMapping("/setSet")
    public String setSet() {
        Set<String> set = new HashSet<String>();
        set.add("heyou");
        set.add("lisi");
        redisService.setSet("setTest", set);
        return "success";
    }
}
