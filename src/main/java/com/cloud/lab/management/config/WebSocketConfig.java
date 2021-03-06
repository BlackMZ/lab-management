package com.cloud.lab.management.config;

import com.cloud.lab.management.constant.WebSocketConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.messaging.handler.invocation.HandlerMethodReturnValueHandler;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

import java.util.List;

/**
 * WebSocket 配置
 *
 * @author Zhuwu
 * @version V1.0
 * @since 2019/1/23
 */
@EnableWebSocketMessageBroker
@Configuration
@Slf4j
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
                // Stomp节点，接收客户端的连接
                .addEndpoint(WebSocketConstants.ENDPOINT)
                // 跨域支持
                .setAllowedOrigins(WebSocketConstants.ALLOWED_ORIGINS)
                // 使用SockJS协议
                .withSockJS();
    }

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration webSocketTransportRegistration) {

    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        /*
            配置消息代理，默认处理以"/topic"为前缀的主题消息，客户端只可以订阅这些前缀的主题。
            客户端订阅的主题前缀，即服务端推送消息的主题前缀
         */
        registry.enableSimpleBroker(WebSocketConstants.APP_TOPIC_PREFIX, WebSocketConstants.USER_PTP_PREFIX);

        // 客户端给服务端发消息的地址前缀，即服务端接收消息的地址前缀
        registry.setApplicationDestinationPrefixes(WebSocketConstants.APPLICATION_DESTINATION_PREFIXE);

        // 用户点对点推送的前缀，默认为/user/
        registry.setUserDestinationPrefix(WebSocketConstants.USER_PTP_PREFIX);
    }

    /**
     * 输入通道参数设置
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(webSocketInterceptor());
    }

    @Override
    public void configureClientOutboundChannel(ChannelRegistration channelRegistration) {

    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> list) {

    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> list) {

    }

    @Override
    public boolean configureMessageConverters(List<MessageConverter> list) {
        return false;
    }

    @Bean
    public WebSocketInterceptor webSocketInterceptor(){
        return new WebSocketInterceptor();
    }

    /**
     * 解决Activiti自动配置类中的一个错误：
     * Parameter 0 of method springAsyncExecutor in org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration required a single bean, but 4 were found
     */
//    @Primary
//    @Bean
//    public TaskExecutor primaryTaskExecutor() {
//        return new SimpleAsyncTaskExecutor();
//    }
}


