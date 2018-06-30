package com.chzero.imooc.ms.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-28 21:53
 * @email 827348260@qq.com
 * @description
 */
@Service
public class MQReceiver{

    @RabbitListener(queues = "mq")
    public void receiver(String message){

    }


}
