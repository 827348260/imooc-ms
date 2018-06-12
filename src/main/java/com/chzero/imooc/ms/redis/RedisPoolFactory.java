package com.chzero.imooc.ms.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-12 14:25
 * @email 827348260@qq.com
 * @description
 */
@Configuration
public class RedisPoolFactory{

    @Autowired
    private RedisConfig redisConfig;

    @Bean
    public JedisPool jedisPoolFactory(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(this.redisConfig.getPoolMaxTotal());
        jedisPoolConfig.setMaxIdle(this.redisConfig.getPoolMaxIdle());
        jedisPoolConfig.setMaxWaitMillis(this.redisConfig.getPoolMaxWait() * 1000);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, this.redisConfig.getHost(), this.redisConfig.getPort(),
                this.redisConfig.getTimeout() * 1000, this.redisConfig.getPassword(), 0);
        return jedisPool;
    }

}
