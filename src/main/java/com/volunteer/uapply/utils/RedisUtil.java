package com.volunteer.uapply.utils;

import com.alibaba.fastjson.JSON;
import com.volunteer.uapply.pojo.Department;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Redis操作工具类
 *
 * @program: uapply
 * @author: GuoShuSong
 * @create: 2020-05-19 14:22
 **/
@Component
public class RedisUtil {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 设置缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean setValue(String key, String value) {
        boolean result = false;
        try {
            stringRedisTemplate.opsForValue().set(String.valueOf(key), value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public Department getValue(String key) {
        String objectJson = stringRedisTemplate.opsForValue().get(key);
        if (objectJson != null) {
            Department object = JSON.parseObject(objectJson, Department.class);
            return object;
        }
        return null;
    }

    /**
     * 更新缓存
     */
    public boolean getAndSet(String key, String value) {
        boolean result = false;
        try {
            stringRedisTemplate.opsForValue().getAndSet(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除缓存
     */
    public boolean delete(String key) {
        boolean result = false;
        try {
            stringRedisTemplate.delete(key);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

