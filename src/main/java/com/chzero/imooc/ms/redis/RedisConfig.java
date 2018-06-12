package com.chzero.imooc.ms.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-12 13:29
 * @email 827348260@qq.com
 * @description 自定义Redis配置类
 */
@Data
@Component
@ConfigurationProperties(prefix = "redis")
public class RedisConfig{

    private String host;
    private int port;
    private String password;
    private int timeout; //秒
    private int poolMaxTotal;
    private int poolMaxIdle;
    private int poolMaxWait; //秒
}
