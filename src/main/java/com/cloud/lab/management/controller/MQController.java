package com.cloud.lab.management.controller;

import com.cloud.lab.management.base.ResponseEntity;
import com.cloud.lab.management.base.ResultModel;
import com.cloud.lab.management.config.HelloSender1;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: John.ma
 * @Description: MQ
 * @Date: 2019/11/14 17:20
 */
@Slf4j
@Api(tags = "MQ")
@RestController
@RequestMapping("/rabbit/mq")
public class MQController {

    @Autowired
    private HelloSender1 helloSender1;


    @ApiOperation(value = "保存相机参数", notes = "保存相机参数")
    @PostMapping(value = "/send")
    public ResponseEntity<ResultModel> saveParameters() {
        helloSender1.send();
        return  new ResponseEntity<>(ResultModel.ok());
    }

    @RabbitListener(queues = "helloQueue")
    public void set(String hello) {
        System.out.println(hello);
    }

}
