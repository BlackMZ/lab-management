package com.cloud.lab.management.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: John.ma
 * @Description:
 * @Date: 2020/5/29 11:50
 */
@Component
@RabbitListener(queues = "helloQueue")
public class HelloReceiver1 {

//    @RabbitHandler
//    public void process(String hello) {
//        System.out.println("Receiver1  : " + hello);
//
//    }
}
