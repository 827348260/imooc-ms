package com.chzero.imooc.ms.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-12 13:38
 * @email 827348260@qq.com
 * @description Redis服务提供类
 */
@Service
public class RedisService{

    @Autowired
    private JedisPool jedisPool;

    /**
     * 获取数据
     * @param prefix
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz){
        Jedis jedis = null;
        try{
            jedis = this.jedisPool.getResource();
            //生成KEY
            String realKey = prefix.getPrefix() + key;
            String str = jedis.get(realKey);
            return this.stringToBean(str, clazz);
        }finally{
            if (jedis != null){ jedis.close(); }
        }
    }

    /**
     * 设置数据
     * @param prefix
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public <T> boolean set(KeyPrefix prefix, String key, T value){
        Jedis jedis = null;
        try{
            jedis = this.jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            String str = this.beanToString(value);
            int seconds = prefix.expireSeconds();
            if (seconds <= 0){
                jedis.set(realKey, str);
            }else {
                jedis.setex(realKey, seconds, str);
            }
            return true;
        }finally{
            if (jedis != null){ jedis.close(); }
        }
    }

    /**
     * 判断key是否存在
     * @param prefix
     * @param key
     * @return
     */
    public boolean exists(KeyPrefix prefix, String key){
        Jedis jedis = null;
        try{
            jedis = this.jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            return jedis.exists(realKey);
        }finally{
            if (jedis != null){ jedis.close(); }
        }
    }

    /**
     * 增加值
     * @param prefix
     * @param key
     * @return
     */
    public Long incr(KeyPrefix prefix, String key){
        Jedis jedis = null;
        try{
            jedis = this.jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            return jedis.incr(realKey);
        }finally{
            if (jedis != null){ jedis.close(); }
        }

    }

    /**
     * 减少值
     * @param prefix
     * @param key
     * @return
     */
    public Long decr(KeyPrefix prefix, String key){
        Jedis jedis = null;
        try{
            jedis = this.jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            return jedis.decr(realKey);
        }finally{
            if (jedis != null){ jedis.close(); }
        }
    }

    private <T> T stringToBean(String str, Class<T> clazz){
        if (str == null || str.length() <= 0 || clazz == null){
            return null;
        }
        if (clazz == int.class || clazz == Integer.class){
            return (T)Integer.valueOf(str);
        }else if (clazz == String.class){
            return (T)str;
        }else if (clazz == long.class || clazz == Long.class){
            return (T)Long.valueOf(str);
        }else{
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }

    private <T> String beanToString(T value){
        if (value == null){
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class){
            return "" + value;
        }else if (clazz == String.class){
            return (String)value;
        }else if (clazz == long.class || clazz == Long.class){
            return "" + value;
        }else{
            return JSON.toJSONString(value);
        }
    }

}
