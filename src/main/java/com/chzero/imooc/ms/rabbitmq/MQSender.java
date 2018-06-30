package com.chzero.imooc.ms.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-28 21:52
 * @email 827348260@qq.com
 * @description
 */
@Service
public class MQSender{

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(Object message){
        this.amqpTemplate.convertAndSend("mq", message);
    }

}
