package com.cloud.lab.management.config;

import org.springframework.stereotype.Component;

/**
 * @Author: John.ma
 * @Description:
 * @Date: 2020/1/6 15:37
 */
@Component
public class ActiveMQServer {

//    @JmsListener(destination = "picture")
    public void receive(String message) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("收到的 message 是：" + message);
    }
}
