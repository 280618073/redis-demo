package com.xdclass.mobile.xdclassmobileredis.controller;

import com.xdclass.mobile.xdclassmobileredis.RedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class RedisController {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private RedisService redisService;

    @RequestMapping("/redis/setAndGet")
    public String serAndGetValue(String name, String value){
        redisTemplate.opsForValue().set(name,value);
        return (String)redisTemplate.opsForValue().get(name);
    }

    @RequestMapping("/redis/setAndGet1")
    public String serAndGetValue2(String name, String value){
        redisService.set(name,value);
        return (String)redisService.get(name);
    }


}
