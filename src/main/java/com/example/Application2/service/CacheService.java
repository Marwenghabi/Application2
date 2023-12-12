package com.example.Application2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CacheService {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public CacheService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void cacheResult(String taskId, String result) {
       
        redisTemplate.opsForValue().set(taskId, result, 1, TimeUnit.HOURS);
    }

    public String getCachedResult(String taskId) {
        
        return (String) redisTemplate.opsForValue().get(taskId);
    }
}
