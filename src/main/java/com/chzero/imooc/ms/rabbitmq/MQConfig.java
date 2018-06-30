package com.chzero.imooc.ms.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-28 21:53
 * @email 827348260@qq.com
 * @description
 */
@Configuration
public class MQConfig{

    @Bean
    public Queue queue(){
        return new Queue("mq", true);
    }

}
