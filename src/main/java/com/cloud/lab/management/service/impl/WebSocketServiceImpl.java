package com.cloud.lab.management.service.impl;

import com.cloud.lab.management.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

/**
 * WebSocketService实现类
 * @author cq
 * @create 2019-08-08
 */
@Service
public class WebSocketServiceImpl implements WebSocketService {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public void formatAndSend(@Nonnull String destination, @Nonnull Object payload, String... args) {
        destination = format(destination, args);
        send(destination, payload);
    }

    @Override
    public void send(@Nonnull String destination, @Nonnull Object payload) {
        simpMessagingTemplate.convertAndSend(destination, payload);
    }

    @Override
    public void sendToUser(@Nonnull String destination, @Nonnull Object payload, @Nonnull String... users) {
        sendToUser(destination, payload, Arrays.asList(users));
    }

    @Override
    public void sendToUser(@Nonnull String destination, @Nonnull Object payload, @Nonnull List<String> userList) {
        userList.forEach(userId -> simpMessagingTemplate.convertAndSendToUser(userId, destination, payload));
    }
}