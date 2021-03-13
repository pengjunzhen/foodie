package com.imooc.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @description Redis controller
 */
@ApiIgnore
@RequestMapping("redis")
@RestController
public class RedisController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/get")
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @GetMapping("/set")
    public String set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
        return "ok";
    }

    @GetMapping("/delete")
    public String delete(String key) {
        redisTemplate.delete(key);
        return  "ok";
    }
}
